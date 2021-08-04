package city.augmented.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 *
 * @param status
 * @param camera
 * @param reconstructionId Reconstruction id
 * @param placeholders Pose describes position and orientation in reconstruction coordinate system. Frame describes 4 points in placeholder coordinate system.
 * @param surfaces
 * @param objects
 */
@JsonClass(generateAdapter = true)
data class LocalizationResultDto(
    var status: StatusDto,
    var camera: CameraDto? = null,
    @Json(name = "reconstruction_id")
    var reconstructionId: Int? = null,
    var placeholders: List<PlaceholderNode3dDto>? = null,
    var surfaces: List<SurfaceDto>? = null,
    var objects: List<ARObjectOldDto>? = null
)

@JsonClass(generateAdapter = true)
data class CameraDto(
    var pose: PoseDto,
    var intrinsics: CameraIntrinsicsDto? = null,
    @Json(name = "height_above_ground_level")
    var heightAboveGroundLevel: Double? = null
)

@JsonClass(generateAdapter = true)
data class CameraIntrinsicsDto(
    /* Focal length */
    var fx: Float,
    var fy: Float,

    /* Principal point */
    var cx: Float,
    var cy: Float
)

@JsonClass(generateAdapter = true)
data class PoseDto(
    var position: Vector3dDto,
    var orientation: QuaternionDto
)

@JsonClass(generateAdapter = true)
data class Vector3dDto(
    var x: Double,
    var y: Double,
    var z: Double
)

@JsonClass(generateAdapter = true)
data class QuaternionDto(
    val x: Float = 0F,
    val y: Float = 0F,
    val z: Float = 0F,
    val w: Float = 1F
)

/** @param pose describes position and orientation in reconstruction coordinate system.
 *  @param frame describes 4 points in placeholder coordinate system.
 */
@JsonClass(generateAdapter = true)
data class PlaceholderNode3dDto(
    @Json(name = "placeholder_id")
    var placeholderId: String,
    var pose: PoseDto,
    var frame: List<Vector3dDto>? = null
)

@JsonClass(generateAdapter = true)
data class SurfaceDto(
    var pose: PoseDto,
    var frame: List<Vector2dDto>
)

@JsonClass(generateAdapter = true)
data class Vector2dDto(
    var x: Float,
    var y: Float
)