package io.roadzen.lithotestapp.formfields

abstract class BaseFormField(
    val id: Int,
    val title: String?,
    val errorMsg: String?,
    var required: Boolean,
    var enabled: Boolean,
    val dependantOn: Int?,
    val dependantValue: String?,
    val isMaster: Boolean
) {
    override fun equals(other: Any?): Boolean {
        if (other !is BaseFormField) return false
        return this.id == other.id
    }
}
//
//enum class FormFieldType {
//    TEXT,
//    MULTI_LINE_TEXT,
//    NUMBER,
//    DROPDOWN,
//    MULTI_SELECT,
//    TOGGLE
//}