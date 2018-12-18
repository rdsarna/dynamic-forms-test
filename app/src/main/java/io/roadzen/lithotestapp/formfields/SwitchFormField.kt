package io.roadzen.lithotestapp.formfields

class SwitchFormField(
    id: Int,
    title: String?,
    errorMsg: String?,
    required: Boolean,
    enabled: Boolean = true,
    dependantOn: Int? = null,
    dependantValue: String? = null,
    isMaster: Boolean = false,
    var value: Boolean = false
) : BaseFormField(id, title, errorMsg, required, enabled, dependantOn, dependantValue, isMaster)