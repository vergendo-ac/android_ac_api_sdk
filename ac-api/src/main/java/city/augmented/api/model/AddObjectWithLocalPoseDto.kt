package city.augmented.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddObjectWithLocalPoseDto(
    @Json(name = "reconstruction_id")
    var reconstructionId: Int,
    var pose: PoseDto,
    val frame: List<Point3dDto>? = null,
    var description: ARObjectDescription
)

@JsonClass(generateAdapter = true)
data class Point3dDto(
    val x: Double,
    val y: Double,
    val z: Double
)

@JsonClass(generateAdapter = true)
data class UpdateObjectWithLocalPoseDto(
    @Json(name = "reconstruction_id")
    var reconstructionId: Int,
    var pose: PoseDto,
    val frame: List<Point3dDto>? = null,
)