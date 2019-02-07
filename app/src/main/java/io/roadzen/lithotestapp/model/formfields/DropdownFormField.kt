package io.roadzen.lithotestapp.model.formfields

import com.google.gson.annotations.SerializedName

class DropdownFormField : SingleSelectionField() {

    var subtype: String? = null
    var default: Int? = null
    var value: Int? = null
    @SerializedName("image_path")
    var imagePath: String? = null
}

enum class DropdownSubtype(val value: String) {
    NONE("none"),
    IMAGE("image")
}