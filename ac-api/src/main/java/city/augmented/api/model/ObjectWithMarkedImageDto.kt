package city.augmented.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ObjectWithMarkedImageDto(
    val placeholder: PlaceholderImageDto,
    val sticker: StickerDto
)

@JsonClass(generateAdapter = true)
data class PlaceholderImageDto(
    val projections: List<ImageProjectionDto>
)

@JsonClass(generateAdapter = true)
data class ImageProjectionDto(
    val points: List<PointDto>,
    val filename: String
)

@JsonClass(generateAdapter = true)
data class PointDto(
    val x: Int,
    val y: Int
)