package io.roadzen.lithotestapp.model.formfields

import com.google.gson.annotations.SerializedName

class DateFormField : BaseFormField() {

    @SerializedName("hint_text")
    var hint: String? = null
    var format: String? = null
    @SerializedName("start_date")
    var startDate: String? = null
    @SerializedName("end_date")
    var endDate: String? = null
    @SerializedName("show_dialog")
    var showDialog: Boolean = true
    var value: String? = null
}