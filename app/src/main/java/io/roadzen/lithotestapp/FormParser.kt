package io.roadzen.lithotestapp

import io.roadzen.lithotestapp.model.Form
import io.roadzen.lithotestapp.model.formfields.BaseFormField
import io.roadzen.lithotestapp.model.formfields.Constants.*
import io.roadzen.lithotestapp.model.formfields.HeaderFormField
import io.roadzen.lithotestapp.model.formfields.TextFormField
import org.json.JSONObject

fun parseJsonToForm(jsonStr: String): Form {

    val formJson = JSONObject(jsonStr)

    val fieldsArray = formJson.getJSONArray(FIELDS.value)

    for (i in 0..(fieldsArray.length() - 1)) {
        val fieldJsonObj = fieldsArray.getJSONObject(i)

        val field: BaseFormField? = createField(fieldJsonObj)
    }

    return Form()
}

private fun createField(fieldJsonObj: JSONObject): BaseFormField? {
    var field: BaseFormField? = null

    when (fieldJsonObj.getString(TYPE.value)) {
        HEADER.value -> {
            field = HeaderFormField()
        }

        TEXT.value -> {
            field = TextFormField()
        }

        AUTOCOMPLETE.value -> {

        }

        DROPDOWN.value -> {

        }

        SWITCH.value -> {

        }

        RADIOGROUP.value -> {

        }

        REGEX_TEXT.value -> {

        }

        DATE.value -> {

        }

        TIME.value -> {

        }
    }

    field?.title = fieldJsonObj.getString(TITLE.value)

    return field
}

fun setTitle(field: BaseFormField, fieldJsonObj: JSONObject) {
    field.title = fieldJsonObj.getString(TITLE.value)
}
