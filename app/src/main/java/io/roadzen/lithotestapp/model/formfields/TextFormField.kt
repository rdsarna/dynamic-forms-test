package io.roadzen.lithotestapp.model.formfields

import com.google.gson.annotations.SerializedName

class TextFormField : BaseFormField() {

    var subtype: String? = null
    var value: String? = null
    var editable: Boolean = true
    @SerializedName("max_length")
    var maxLength: Int? = null
}

enum class TextSubtype(val value: String) {
    NONE("none"),
    ALL_CAPS("all_caps"),
    MULTILINE("multiline"),
    NUMBER("number"),
    PHONE("phone"),
    EMAIL("email")
}