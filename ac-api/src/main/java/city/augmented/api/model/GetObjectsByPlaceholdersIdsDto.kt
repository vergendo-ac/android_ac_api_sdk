package city.augmented.api.model

import com.squareup.moshi.Json

data class GetObjectsByPlaceholdersIdsDto(
    @Json(name = "p_placeholder_ids")
    val placeholdersIds: List<Int>,
    @Json(name = "p_language")
    val language: LanguageType? = null
)

enum class LanguageType {
    EN, RU
}