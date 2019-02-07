package io.roadzen.lithotestapp.model.formfields

import io.roadzen.lithotestapp.model.formfields.dependencies.BaseDependency

abstract class BaseFormField {

    var id: String = ""
    var title: String? = null
    var required: Boolean = false
    var enabled: Boolean = true
    var dependencies: List<BaseDependency>? = null
    var triggers: List<String>? = null

    override fun equals(other: Any?): Boolean {
        if (other !is BaseFormField) return false
        return this.id == other.id
    }
}