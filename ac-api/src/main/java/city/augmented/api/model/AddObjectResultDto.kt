package city.augmented.api.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddObjectResult(
    var status: StatusDto,
    @Json(name = "objects_info")
    var objectsInfo: List<ARObjectOldDto>? = null
)

