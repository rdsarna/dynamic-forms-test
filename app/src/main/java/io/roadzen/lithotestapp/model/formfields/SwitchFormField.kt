package io.roadzen.lithotestapp.model.formfields

import com.google.gson.annotations.SerializedName

class SwitchFormField : BaseFormField() {

    var value: Boolean = false
    @SerializedName("true_option")
    var trueOption: String? = null
    @SerializedName("false_option")
    var falseOption: String? = null
}