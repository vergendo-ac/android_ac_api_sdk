package city.augmented.api.model

data class LocationDto(
    val latitude: Double,
    val longitude: Double,
    val altitude: Double? = null,
    val hDop: Float? = null
)