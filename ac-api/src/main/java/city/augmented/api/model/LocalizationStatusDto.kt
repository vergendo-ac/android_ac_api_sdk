package city.augmented.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class LocalizationStatusDto(
    val status: Status
)

/**
 * @param code 0 if Image is localized; 1 if Fail to localize image
 */
@JsonClass(generateAdapter = true)
data class Status(
    var code: Int,
    var message: String
)