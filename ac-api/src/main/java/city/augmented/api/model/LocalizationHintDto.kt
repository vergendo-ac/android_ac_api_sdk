package city.augmented.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * List of reconstruction identifiers.
 * The service will perform localization sequentially in each reconstruction according to the order
 * specified in the list until the first successful result is obtained.
 * If hint_only is true, the service will localize only in the specified reconstructions.
 * If hint_only is false, the service will continue localization attempts in the nearest reconstructions
 */
@JsonClass(generateAdapter = true)
data class LocalizationHintDto(
    val reconstructions: List<Int>,
    @Json(name = "hint_only")
    val hintOnly: Boolean = false
)