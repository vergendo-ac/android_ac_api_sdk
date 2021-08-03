package city.augmented.api.infrastructure

import city.augmented.api.entity.BooleanString
import city.augmented.api.entity.InfoStickerType
import city.augmented.api.entity.Object3dSubtype
import city.augmented.api.entity.StickerType
import com.squareup.moshi.FromJson
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson

object Serializer {
    @JvmStatic
    val moshiBuilder: Moshi.Builder = Moshi.Builder()
        .add(BooleanStringAdapter())
        .add(StickerTypeAdapter())
        .add(InfoStickerTypeAdapter())
        .add(Object3DSubtypeAdapter())

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

