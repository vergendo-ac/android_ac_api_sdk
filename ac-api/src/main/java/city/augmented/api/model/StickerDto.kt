package city.augmented.api.model

import city.augmented.api.entity.BooleanString
import city.augmented.api.entity.InfoStickerType
import city.augmented.api.entity.Object3dSubtype
import city.augmented.api.entity.StickerType
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ARObjectOldDto(
    var placeholder: PlaceholderOldDto,
    var sticker: StickerDto
)

@JsonClass(generateAdapter = true)
data class PlaceholderOldDto(
    @Json(name = "placeholder_id")
    var placeholderId: String
)

@JsonClass(generateAdapter = true)
class StickerDto(
    @Json(name = "sticker_id")
    val stickerId: String, // common required
    val type: StickerType, // common required
    @Json(name = "sticker_text")
    val stickerText: String, // common required
    val path: String, // common required
    val description: String = "", // common not required
    @Json(name = "sticker_type")
    val infoStickerType: InfoStickerType = InfoStickerType.OTHER, // InfoStickerOld required
    @Json(name = "width")
    val videoWidth: String = "", // VideoOld required
    @Json(name = "height")
    val videoHeight: String = "", // VideoOld required
    @Json(name = "subtype")
    val objectSubType: Object3dSubtype = Object3dSubtype.OBJECT, // Model3dOld required
    @Json(name = "model_id")
    val objectModelId: String = "", // Model3dOld required
    @Json(name = "model_scale")
    val objectModelScale: String = "", // Model3dOld required
    // 0 or 1
    @Json(name = "grounded")
    val objectIsGrounded: BooleanString = BooleanString(false), // Model3dOld required
    // 0 or 1
    @Json(name = "vertically_aligned")
    val objectIsVerticallyAligned: BooleanString = BooleanString(false), // Model3dOld required

    // custom properties

    // TA sticker
    @Json(name = "Address")
    val address: String = "",
    @Json(name = "Feedback amount")
    val feedbackAmount: String = "",
    @Json(name = "Image")
    val image: String = "",
    @Json(name = "Phone number")
    val phoneNumber: String = "",
    @Json(name = "Price category")
    val priceCategory: String = "",
    @Json(name = "Rating")
    val rating: String = "",
    @Json(name = "Site")
    val site: String = "",
    @Json(name = "url_ta")
    val urlTa: String = ""
)
