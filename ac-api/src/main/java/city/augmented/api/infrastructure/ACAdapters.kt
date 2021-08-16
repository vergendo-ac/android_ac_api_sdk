package city.augmented.api.infrastructure

import city.augmented.api.entity.*
import city.augmented.api.model.ImageRotation
import city.augmented.api.model.LanguageType
import city.augmented.api.model.StatusCode
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson

object ACAdapters {
    @JvmStatic
    val moshiBuilder: Moshi.Builder = Moshi.Builder()
        .add(BooleanStringAdapter())
        .add(StickerTypeAdapter())
        .add(InfoStickerTypeAdapter())
        .add(Object3DSubtypeAdapter())
        .add(StatusCodeAdapter())
        .add(ImageRotationAdapter())
        .add(LanguageTypeAdapter())
        .add(PlatformTypeAdapter())

    @JvmStatic
    val moshi: Moshi by lazy {
        moshiBuilder.build()
    }
}

class BooleanStringAdapter {
    @ToJson
    fun toJson(booleanString: BooleanString): String = booleanString.toString()

    @FromJson
    fun fromJson(stringValue: String) = BooleanString.fromString(stringValue)
}

class StickerTypeAdapter {
    @ToJson
    fun toJson(stickerType: StickerType): String = stickerType.name

    @FromJson
    fun fromJson(stringValue: String) = StickerType.fromString(stringValue)
}

class InfoStickerTypeAdapter {
    @ToJson
    fun toJson(infoStickerType: InfoStickerType): String = infoStickerType.toString()

    @FromJson
    fun fromJson(stringValue: String) = InfoStickerType.fromString(stringValue)
}

class Object3DSubtypeAdapter {
    @ToJson
    fun toJson(subType: Object3dSubtype): String = subType.name

    @FromJson
    fun fromJson(stringValue: String) = Object3dSubtype.fromString(stringValue)
}

class StatusCodeAdapter {
    @ToJson
    fun toJson(statusCode: StatusCode): Int = statusCode.ordinal

    @FromJson
    fun fromJson(intValue: Int): StatusCode = when (intValue) {
        0 -> StatusCode.SUCCESS
        else -> StatusCode.FAILURE
    }
}

class ImageRotationAdapter {
    @ToJson
    fun toJson(statusCode: ImageRotation): Int = statusCode.angle

    @FromJson
    fun fromJson(intValue: Int): ImageRotation = when (intValue) {
        90 -> ImageRotation.ROTATION_90
        180 -> ImageRotation.ROTATION_180
        270 -> ImageRotation.ROTATION_270
        else -> ImageRotation.ROTATION_0
    }
}

class LanguageTypeAdapter {
    @ToJson
    fun toJson(language: LanguageType): String = language.name.lowercase()

    @FromJson
    fun fromJson(stringValue: String): LanguageType = when (stringValue) {
        "ru" -> LanguageType.RU
        else -> LanguageType.EN
    }
}

class PlatformTypeAdapter {
    @ToJson
    fun toJson(platform: PlatformType): String = platform.toString()

    @FromJson
    fun fromJson(stringValue: String): PlatformType = PlatformType.fromString(stringValue)
}