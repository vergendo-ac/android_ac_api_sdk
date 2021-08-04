package city.augmented.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UpdateObjectContentDto(
    val sticker: Map<String, String>
)