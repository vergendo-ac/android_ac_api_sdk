package city.augmented.api

import arrow.core.Either
import city.augmented.api.apis.LocalizerApi
import city.augmented.api.entity.ApiError
import city.augmented.api.infrastructure.ApiClient
import city.augmented.api.model.ImageDescriptionDto
import city.augmented.api.model.LocalizationResultDto
import city.augmented.api.model.LocalizationStatusDto
import city.augmented.api.model.LocationDto
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class LocalizerApiClient(private val apiClient: ApiClient) {
    private val localizer: LocalizerApi
        get() = apiClient.createService(LocalizerApi::class.java)

    suspend fun localize(
        description: ImageDescriptionDto,
        imageBytes: ByteArray
    ): Either<ApiError, LocalizationResultDto> = safeInvoke {
        localizer.localize(
            description,
            MultipartBody.Part.createFormData(
                "image",
                null,
                imageBytes.toRequestBody(
                    "multipart/form-data".toMediaTypeOrNull(),
                    0,
                    imageBytes.size
                )
            )
        )
    }

    suspend fun prepareLocalizer(location: LocationDto): Either<ApiError, LocalizationStatusDto> =
        safeInvoke {
            localizer.prepare(
                location.latitude,
                location.longitude,
                location.altitude,
                location.hDop
            )
        }
}