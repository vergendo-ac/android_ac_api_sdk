package city.augmented.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @param code 0 if Image is localized; 1 if Fail to localize image
 */
@JsonClass(generateAdapter = true)
data class LocalizationStatusDto(
    @Json(name = "code")
    var code: Int,
    @Json(name = "message")
    var message: String
)