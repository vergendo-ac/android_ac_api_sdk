package city.augmented.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddObjectWithGeoPoseDto(
    @Json(name = "reconstruction_id")
    var reconstructionId: Int,
    var geopose: GeoPoseDto,
    var description: ARObjectDescription
)

@JsonClass(generateAdapter = true)
data class ARObjectDescription(
    val sticker: StickerDto
)

@JsonClass(generateAdapter = true)
data class GeoPoseDto(
    var longitude: Double,
    var latitude: Double,
    @Json(name = "ellipsoidHeight")
    var ellipsoidHeight: Double,
    var quaternion: QuaternionDto
)

@JsonClass(generateAdapter = true)
data class UpdateObjectWithGeoPoseDto(
    @Json(name = "reconstruction_id")
    var reconstructionId: Int,
    var geopose: GeoPoseDto
)

