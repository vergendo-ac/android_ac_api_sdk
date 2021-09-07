package city.augmented.api.entity

import com.squareup.moshi.JsonDataException

enum class PlatformType {
    ANDROID, UNITY_IOS, UNITY_ANDROID, UWP;

    override fun toString(): String = when (this) {
        ANDROID -> "android_native"
        UNITY_IOS -> "ios"
        UNITY_ANDROID -> "android"
        UWP -> "uwp"
    }

    companion object {
        fun fromString(value: String): PlatformType = when (value) {
            "android_native" -> ANDROID
            "ios" -> UNITY_IOS
            "android" -> UNITY_ANDROID
            "uwp" -> UWP
            else -> throw JsonDataException("Unexpected json value for enum type Platform")
        }
    }
}