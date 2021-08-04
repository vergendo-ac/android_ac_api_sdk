package city.augmented.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageDescriptionDto(
    val gps: GpsDto,
    val rotation: Int = 0
)