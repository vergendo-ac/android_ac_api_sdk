package city.augmented.api.model

import com.squareup.moshi.JsonClass

enum class StatusCode {
    SUCCESS,
    FAILURE
}

@JsonClass(generateAdapter = true)
data class StatusDto(
    var code: StatusCode,
    var message: String
)

@JsonClass(generateAdapter = true)
data class LocalizationStatusDto(
    val status: StatusDto
)

@JsonClass(generateAdapter = true)
data class UpdateObjectStatusDto(
    val status: StatusDto
)
