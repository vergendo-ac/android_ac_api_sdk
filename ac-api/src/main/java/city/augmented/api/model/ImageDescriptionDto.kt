package city.augmented.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ImageDescriptionDto(
    val gps: GpsDto,
    val rotation: ImageRotation = ImageRotation.ROTATION_0
)

enum class ImageRotation(val angle: Int) {
    ROTATION_0(0),
    ROTATION_90(90),
    ROTATION_180(180),
    ROTATION_270(270),
}