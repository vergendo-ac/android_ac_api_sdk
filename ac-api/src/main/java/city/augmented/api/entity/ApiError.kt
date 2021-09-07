package city.augmented.api.entity

sealed class ApiError(val message: String) {
    data class UnexpectedError(val customMessage: String) : ApiError(customMessage)
    data class CastError(val customMessage: String) : ApiError(customMessage)
    data class JsonParsingException(val customMessage: String) : ApiError(customMessage)
    data class UnknownServerException(val customMessage: String) : ApiError(customMessage)
    data class BadRequest(val customMessage: String) : ApiError(customMessage)
    data class UnexpectedRedirection(val customMessage: String) : ApiError(customMessage)
    data class UnknownConnectionError(val customMessage: String) : ApiError(customMessage)
    object EmptyResponseBody : ApiError("Request successful but body is null")
    object CantLocalize : ApiError("Can not localize with this image")
}