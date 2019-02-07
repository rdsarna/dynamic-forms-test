package io.roadzen.lithotestapp.model.formfields

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class AutocompleteFormField : BaseFormField() {

    @SerializedName("hint_text")
    var hint: String? = null
    var path: String? = null
    var value: JsonObject? = null
    @SerializedName("value_order")
    var valueOrder: List<String>? = null
}