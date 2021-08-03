package city.augmented.api.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Gps(
    val latitude: Double,
    val longitude: Double,
    val altitude: Double? = null
)
