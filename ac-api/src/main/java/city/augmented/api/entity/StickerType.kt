package city.augmented.api.entity

enum class StickerType {
    OBJECT3D, INFOSTICKER, VIDEO, IMAGE, CUSTOM;

    companion object {
        fun fromString(name: String) = when (name) {
            "3D" -> OBJECT3D
            "VIDEO" -> VIDEO
            "IMAGE" -> IMAGE
            "CUSTOM" -> CUSTOM
            else -> INFOSTICKER
        }
    }
}