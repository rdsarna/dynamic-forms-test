package io.roadzen.lithotestapp.formfields

class TextFormField(
    id: Int,
    title: String?,
    errorMsg: String?,
    required: Boolean,
    enabled: Boolean = true,
    dependantOn: Int? = null,
    dependantValue: String? = null,
    isMaster: Boolean = false,
    val hint: String? = null,
    var value: String? = null
) : BaseFormField(id, title, errorMsg, required, enabled, dependantOn, dependantValue, isMaster)

