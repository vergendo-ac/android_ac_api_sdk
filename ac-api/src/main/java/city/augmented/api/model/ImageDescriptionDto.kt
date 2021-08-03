package city.augmented.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageDescriptionDto(
    val gps: Gps,
    val rotation: Int
)