package city.augmented.api.entity

data class GpsArea(
    val latitude: Double,
    val longitude: Double,
    val radius: Float = 500f
)