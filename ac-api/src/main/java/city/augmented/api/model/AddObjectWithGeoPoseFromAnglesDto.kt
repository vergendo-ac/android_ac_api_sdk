package city.augmented.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddObjectWithGeoPoseFromAnglesDto(
    @Json(name = "reconstruction_id")
    val reconstructionId: Int,
    val geopose: GeoPoseAnglesDto,
    val description: ARObjectDescription
)

@JsonClass(generateAdapter = true)
data class GeoPoseAnglesDto(
    val longitude: Double,
    val latitude: Double,
    @Json(name = "ellipsoidHeight")
    val ellipsoidHeight: Double,
    @Json(name = "angles")
    val eulerAngles: EulerAnglesDto
)

@JsonClass(generateAdapter = true)
data class EulerAnglesDto(
    val heading: Double,
    val pitch: Double,
    val roll: Double
)

@JsonClass(generateAdapter = true)
data class UpdateObjectWithGeoPoseFromAnglesDto(
    @Json(name = "reconstruction_id")
    val reconstructionId: Int,
    val geopose: GeoPoseAnglesDto
)

