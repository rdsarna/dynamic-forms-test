package io.roadzen.lithotestapp.formfields

class DropdownFormField(
    id: Int,
    title: String?,
    errorMsg: String?,
    required: Boolean,
    enabled: Boolean = true,
    dependantOn: Int? = null,
    dependantValue: String? = null,
    isMaster: Boolean = false,
    val options: List<String>,
    var chosenValue: Int,
    val hint: String?
) : BaseFormField(id, title, errorMsg, required, enabled, dependantOn, dependantValue, isMaster)