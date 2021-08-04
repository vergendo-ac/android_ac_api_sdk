package city.augmented.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlaceholderWithGpsResultDto(
    val placeholder: PlaceholderWithGpsDto,
)

@JsonClass(generateAdapter = true)
data class PlaceholderWithGpsDto(
    @Json(name = "placeholder_id")
    val placeholderId: String,
    val gps: GpsDto
)