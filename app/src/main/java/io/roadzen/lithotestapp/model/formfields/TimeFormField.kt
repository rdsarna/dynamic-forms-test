package io.roadzen.lithotestapp.model.formfields

import com.google.gson.annotations.SerializedName

class TimeFormField : BaseFormField() {

    var format: String? = null
    @SerializedName("show_dialog")
    var showDialog: Boolean = true
    var editable: Boolean = false
    var value: String? = null
}