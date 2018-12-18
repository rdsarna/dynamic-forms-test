package io.roadzen.lithotestapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.thejuki.kformmaster.helper.FormLayouts
import com.thejuki.kformmaster.model.BaseFormElement
import com.thejuki.kformmaster.model.FormPickerDropDownElement
import com.thejuki.kformmaster.model.FormSingleLineEditTextElement
import com.thejuki.kformmaster.model.FormSwitchElement
import io.roadzen.lithotestapp.formfields.BaseFormField
import io.roadzen.lithotestapp.formfields.DropdownFormField
import io.roadzen.lithotestapp.formfields.SwitchFormField
import io.roadzen.lithotestapp.formfields.TextFormField
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var formBuilder: FormBuildHelper
    private val questions = getQuestions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        formBuilder = FormBuildHelper(this, formLayouts = FormLayouts(
            text = R.layout.custom_element_text
        ))
        formBuilder.attachRecyclerView(this, formRecyclerView)

        populateForm()
    }

    private fun populateForm() {
        val elements: MutableList<BaseFormElement<*>> = mutableListOf()

        val masterFields = questions.filter { it.isMaster }

        questions.forEach { field ->
            var element: BaseFormElement<*>? = null

            when(field) {
                is TextFormField -> {
                    element = FormSingleLineEditTextElement(field.id).apply {
                        title = field.title
                        required = field.required
                        hint = field.hint
                        rightToLeft = false
                        displayDivider = false
                        titleTextColor = android.R.color.darker_gray

                        field.dependantOn?.let { masterId ->
                            val masterField = masterFields.first { it.id == masterId }
                            visible = getVisibility(masterField, field)
                        }
                    }
                }

                is DropdownFormField -> {
                    element = FormPickerDropDownElement<String>(field.id).apply {
                        title = field.title
//                        arrayAdapter = ArrayAdapter(this@MainActivity, android.R.layout.simple_list_item_1,
//                            field.options)
                        options = field.options
                        required = field.required
                        hint = field.hint

                        field.dependantOn?.let { masterId ->
                            val masterField = masterFields.first { it.id == masterId }
                            visible = getVisibility(masterField, field)
                        }
                    }
                }

                is SwitchFormField -> {
                    element = FormSwitchElement<String>(field.id).apply {
                        title = field.title
                        onValue = "Yes"
                        offValue = "No"
                        value = if (field.value) "Yes" else "No"

                        field.dependantOn?.let { masterId ->
                            val masterField = masterFields.first { it.id == masterId }
                            visible = getVisibility(masterField, field)
                        }

                        if (field.isMaster) {
                            valueObservers.add { newValue, _ ->
                                valueChanged(field.id, if (newValue == "Yes") "true" else "false")
                            }
                        }
                    }
                }
            }

            element?.let { elements.add(it) }
        }

        formBuilder.addFormElements(elements)
    }

    private fun getVisibility(masterField: BaseFormField, field: BaseFormField): Boolean {
        return when (masterField) {
            is SwitchFormField -> {
                val value = if (masterField.value) "true" else "false"
                value == field.dependantValue
            }
            else -> true
        }
    }

    private fun valueChanged(id: Int, newValue: String) {
        questions.forEach {
            if (it.dependantOn == id) {
                val element: BaseFormElement<*> = formBuilder.getFormElement(it.id)
                element.visible = newValue == "true"
                formBuilder.onValueChanged(element)
            }
        }
    }
}
