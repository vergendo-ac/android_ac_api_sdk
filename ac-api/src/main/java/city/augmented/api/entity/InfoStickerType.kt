package city.augmented.api.entity

enum class InfoStickerType {
    REST,
    SHOP,
    PLACE,
    OTHER,
    TEXT,
    VIDEO;

    override fun toString() =
        when (this) {
            REST -> "restaurant"
            SHOP -> "shop"
            PLACE -> "place"
            OTHER -> "other"
            TEXT -> "text"
            VIDEO -> "video"
        }

    companion object {
        fun fromString(string: String): InfoStickerType {
            return when (string) {
                "restaurant" -> REST
                "shop" -> SHOP
                "place" -> PLACE
                "text" -> TEXT
                "video" -> VIDEO
                else -> OTHER
            }
        }
    }
}