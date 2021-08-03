package city.augmented.api.entity

enum class Object3dSubtype {
    OBJECT, NAVMESH, TRANSFER, CUSTOM;

    companion object {
        fun fromString(name: String) = when (name) {
            "NAVMESH" -> NAVMESH
            "TRANSFER" -> TRANSFER
            "CUSTOM" -> CUSTOM
            else -> OBJECT
        }
    }
}
