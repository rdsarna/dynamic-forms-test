package io.roadzen.lithotestapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.thejuki.kformmaster.helper.email
import com.thejuki.kformmaster.helper.form
import com.thejuki.kformmaster.model.BaseFormElement
import io.roadzen.lithotestapp.kform.switch.FormRZSwitchElement
import io.roadzen.lithotestapp.kform.checkbox.FormRZCheckBoxElement
import io.roadzen.lithotestapp.kform.checkbox.FormRZCheckBoxViewBinder
import io.roadzen.lithotestapp.kform.dropdown.FormRZDropdownElement
import io.roadzen.lithotestapp.kform.dropdown.FormRZDropdownViewBinder
import io.roadzen.lithotestapp.kform.switch.FormRZSwitchViewBinder
import io.roadzen.lithotestapp.kform.textinput.FormRZTextInputElement
import io.roadzen.lithotestapp.kform.textinput.FormRZTextInputViewBinder
import io.roadzen.lithotestapp.model.Form
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var formBuilder: FormBuildHelper

    private var form: Form? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        formBuilder = form(this, formRecyclerView) {
            email(1) {
                title = "Email Address"
                validityCheck = {
                    value?.equals("abc") == true
                }
                valueObservers.add { _, _ ->
                    Log.d("SPECIAL", "isValid: $isValid")
                }
            }
            email(1) {
                title = "Email Address"
            }
        }

//        formBuilder = FormBuildHelper(this, formLayouts = FormLayouts(
//            header = R.layout.rz_form_element_header,
//            checkBox = R.layout.rz_form_element_checkbox,
//            switch = R.layout.rz_form_element_switch
//        ))
//        formBuilder.attachRecyclerView(this, formRecyclerView)
//
//        form = gson.fromJson(json, Form::class.java)
//        populateForm()
//
//        fab.setOnClickListener {
//            formBuilder.elements.asIterable().forEach { it.enabled = !it.enabled }
//        }
    }

    private fun populateForm() {
        val elements: MutableList<BaseFormElement<*>> = mutableListOf()

        var tag = 0
        elements.add(FormRZTextInputElement(tag++).apply {
            displayDivider = false
            title = "Policy Holder's Name"
            hint = "Enter name"
            required = false
            rightToLeft = false
            leftIconDrawable = getDrawable(android.R.drawable.ic_menu_camera)
        })

        elements.add(FormRZTextInputElement(tag++).apply {
            displayDivider = false
            title = "Policy Holder's Name"
            hint = "Enter name"
            required = false
            rightToLeft = false
            leftIconDrawable = getDrawable(android.R.drawable.ic_menu_camera)
        })

        elements.add(FormRZDropdownElement<String>(tag++).apply {
            displayDivider = false
            title = "Insurance Company Name"
            required = false
            options = listOf("Select Company", "ICICI Lombard", "TATA AIA Motor", "Bharti AXA")
        })

        elements.add(FormRZCheckBoxElement<Boolean>(tag++).apply {
            displayDivider = false
            title = "Driver same as Policy Holder?"
            value = false
            checkedValue = true
            unCheckedValue = false
        })

        elements.add(FormRZSwitchElement<Boolean>(tag++).apply {
            displayDivider = false
            title = "Is requester same as Policy Holder?"
            value = false
            onValue = true
            offValue = false
        })

        formBuilder.registerCustomViewBinder(FormRZTextInputViewBinder(this, formBuilder).viewBinder)
        formBuilder.registerCustomViewBinder(FormRZDropdownViewBinder(this, formBuilder).viewBinder)
        formBuilder.registerCustomViewBinder(FormRZSwitchViewBinder(this, formBuilder).viewBinder)
        formBuilder.registerCustomViewBinder(FormRZCheckBoxViewBinder(this, formBuilder).viewBinder)

        formBuilder.addFormElements(elements)
    }

//    private fun populateForm() {
//        val elements: MutableList<BaseFormElement<*>> = mutableListOf()
//
//        val fieldSize = form?.fields?.size ?: 0
//        for (i in 0 until fieldSize) {
//            val formField = form?.fields?.get(i)
//            var formElement: BaseFormElement<*>? = null
//
//            when(formField) {
//                is HeaderFormField -> {
//                    formElement = FormHeader(i, formField.title).apply {
//                        titleTextColor = ContextCompat.getColor(this@MainActivity, R.color.formHeader)
//                        backgroundColor = ContextCompat.getColor(this@MainActivity, android.R.color.white)
//                        displayDivider = false
//                    }
//                }
//
//                is TextFormField -> {
////                    formElement = when(formField.subtype) {
////                        TextSubtype.MULTILINE.value -> FormMultiLineEditTextElement(i)
////                        TextSubtype.NUMBER.value -> FormNumberEditTextElement(i)
////                        TextSubtype.PHONE.value -> FormPhoneEditTextElement(i)
////                        TextSubtype.EMAIL.value -> FormEmailEditTextElement(i)
////                        else -> FormSingleLineEditTextElement(i)
////                    } as BaseFormElement<String>
//
//                    formElement = FormRZTextInputElement(i)
//
//                    formElement.apply {
//                        displayDivider = false
//                        title = formField.title
//                        hint = formField.title
//                        required = formField.required
//                        enabled = formField.editable
//                        formField.maxLength?.let { maxLength = it }
//                        formField.value?.let { value = it }
//                        rightToLeft = false
//                    }
//                }
//
//                is RadiogroupFormField -> {
//                    formElement = FormRZRadioGroupElement<String>(i).apply {
//                        if (formField.title != null) title = formField.title
//                        else displayTitle = false
//                        displayDivider = false
//                        options = formField.options
//                        if (formField.value == null)
//                            formField.default?.let { value = formField.options?.get(it) }
//                        else
//                            formField.value?.let { value = formField.options?.get(it) }
//
//                        if (formField.triggers != null && formField.triggers?.isNotEmpty() == true) {
//                            valueObservers.add { _, element -> onFormValueChanged(element) }
//                        }
//                    }
//                }
//
//                is ButtonFormField -> {
//                    formElement = FormButtonElement(i).apply {
//                        value = formField.title
//
//                    }
//                }
//            }
//
//            formElement?.let { elements.add(it) }
//        }
//        formBuilder.registerCustomViewBinder(FormRZTextInputViewBinder(this, formBuilder).viewBinder)
//        formBuilder.registerCustomViewBinder(FormRZRadioGroupViewBinder(this, formBuilder, null).viewBinder)
//
//        formBuilder.addFormElements(elements)
//    }
//
//    private fun onFormValueChanged(element: BaseFormElement<String>) {
//        val changedMasterField = form?.fields?.get(element.tag) as? RadiogroupFormField
//        val masterId = changedMasterField?.id
//
//        val affectedFields = form?.fields?.filter { field ->
//            field.dependencies?.forEach {
//                return@filter it.master == masterId
//            }
//            false
//        }
//
//        affectedFields?.forEach { field ->
//            val affectedTag = form?.fields?.indexOf(field) ?: return
//            val affectedElement = formBuilder.getFormElement<BaseFormElement<*>>(affectedTag)
//            field.dependencies?.forEach { dependency ->
//                when(dependency) {
//                    is SingleSelectionVisibilityDependency -> {
//                        val masterField = form?.fields?.first { dependency.master == it.id } as SingleSelectionField
//                        val masterTag = form?.fields?.indexOf(masterField) ?: return
//                        val masterElement = formBuilder.getFormElement<BaseFormElement<*>>(masterTag)
//
//                        val value = masterElement.value as? String
//                        val valueIndex = masterField.options?.indexOf(value) ?: -1
//
//                        affectedElement.visible = if (dependency.lookFor?.contains(valueIndex) == true) {
//                            dependency.conditionMet == "visible"
//                        } else {
//                            dependency.conditionNotMet == "visible"
//                        }
//                    }
//                }
//            }
//        }
//    }

}
