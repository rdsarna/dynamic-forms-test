package io.roadzen.lithotestapp.model.formfields

import com.google.gson.annotations.SerializedName

class RegexTextFormField : BaseFormField() {

    @SerializedName("hint_text")
    var hint: String? = null
    var value: String? = null
    var regex: String? = null
}