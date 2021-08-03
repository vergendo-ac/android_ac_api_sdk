package city.augmented.api.entity

class BooleanString(val value: Boolean) {

    companion object {
        fun fromString(stringValue: String) = BooleanString(
            try {
                when (stringValue.toInt()) {
                    1 -> true
                    else -> false
                }
            } catch (e: NumberFormatException) {
                false
            }
        )
    }

    override fun toString(): String = if (value) "1" else "0"
}