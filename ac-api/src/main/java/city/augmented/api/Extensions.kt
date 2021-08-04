package city.augmented.api

import android.util.Log
import arrow.core.Either
import arrow.core.Left
import arrow.core.Right
import city.augmented.api.entity.ApiError
import com.squareup.moshi.JsonDataException
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import retrofit2.Response

inline fun <reified T> safeInvoke(apiInvoke: () -> Response<T>): Either<ApiError, T> =
    try {
        apiInvoke().bodyOrError()
    } catch (e: Exception) {
        Log.e("safeInvoke", "Connection error: ${e.message}")
        Left(
            when (e) {
                is JsonDataException -> ApiError.JsonParsingException(e.message ?: "wtf")
                else -> ApiError.UnexpectedError(e.message ?: "wtf")
            }
        )
    }

inline fun <reified T> Response<T>.bodyOrError(): Either<ApiError, T> =
    if (isSuccessful)
        if (body() != null)
            try {
                Right(body() as T)
            } catch (e: ClassCastException) {
                Log.e("Response.bodyOrError", "Cannot cast response body: ${e.message}")
                Left(ApiError.CastError(e.message ?: "wtf"))
            }
        else
            Left(ApiError.EmptyResponseBody)
    else
        Left(resolveResponseError(code(), errorBody()?.string()))

fun resolveResponseError(httpCode: Int, message: String?): ApiError {
    val errorMessage = "code: $httpCode, message: $message"
    return when (httpCode / 100) {
        3 -> ApiError.UnexpectedRedirection("Redirection. $errorMessage")
        4 -> ApiError.BadRequest("Bad request. $errorMessage")
        5 -> ApiError.UnknownServerException("Server error. $errorMessage")
        else -> ApiError.UnknownConnectionError("Unknown connection error. $errorMessage")
    }
}

fun ByteArray.toMultipartBody() = MultipartBody.Part.createFormData(
    "image",
    null,
    toRequestBody(
        "multipart/form-data".toMediaTypeOrNull(),
        0,
        size
    )
)

fun <A, B> Either<A, B>.ifLeft(invokeLeft: (A) -> Unit): Either<A, B> {
    if (this is Either.Left) invokeLeft(a)
    return this
}

fun <A, B> Either<A, B>.ifRight(invokeRight: (B) -> Unit): Either<A, B> {
    if (this is Either.Right) invokeRight(b)
    return this
}