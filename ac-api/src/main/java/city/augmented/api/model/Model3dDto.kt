package city.augmented.api.model

import city.augmented.api.entity.PlatformType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Model3dDto(
    val id: String,
    val name: String,
    @Json(name = "preview_url")
    val previewUrl: String,
    val refs: List<Model3dRefDto>
)

@JsonClass(generateAdapter = true)
data class Model3dRefDto(
    val platform: PlatformType,
    val url: String
)