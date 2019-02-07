package io.roadzen.lithotestapp.kform.textinput

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder
import com.github.vivchar.rendererrecyclerviewadapter.ViewState
import com.github.vivchar.rendererrecyclerviewadapter.ViewStateProvider
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.thejuki.kformmaster.view.BaseFormViewBinder
import io.roadzen.lithotestapp.R

class FormRZTextInputViewBinder(private val context: Context,
                                private val formBuilder: FormBuildHelper
) : BaseFormViewBinder() {
    var viewBinder = ViewBinder(
        R.layout.rz_form_element_text_input,
        FormRZTextInputElement::class.java, { model, finder, _ ->
            val textViewError = finder.find(R.id.formElementError) as AppCompatTextView
            val mainViewLayout = finder.find(R.id.formElementMainLayout) as LinearLayout
            val textViewTitle = finder.find(R.id.formElementTitle) as AppCompatTextView
            val baseLine = finder.find(R.id.formElementBaseLine) as View
            val inputParentLayout = finder.find(R.id.formElementInputParentLayout) as CardView
            val inputParentLayout2 = finder.find(R.id.formElementInputParentLayout2) as ConstraintLayout
            val leftIcon = finder.find(R.id.formElementIcon) as ImageView
            val itemView = finder.getRootView() as View
            baseSetup(formElement = model, textViewTitle = textViewTitle, textViewError = textViewError, itemView = itemView,
                dividerView = null, mainViewLayout = mainViewLayout)

            // Set up EditText
            val editTextValue = finder.find(R.id.formElementValue) as AppCompatEditText
            model.editView = editTextValue
            editTextValue.setText(model.valueAsString)
            setEditTextFocusEnabled(editTextValue, itemView)

            // Set all the default colors
            if (model.baseLineColor == null) model.baseLineColor = getColor(android.R.color.darker_gray)
            if (model.titleTextColor == null) model.titleTextColor = getColor(android.R.color.black)
            if (model.errorTextColor == null) model.errorTextColor = getColor(com.thejuki.kformmaster.R.color.colorFormMasterElementErrorTitle)
            if (model.titleFocusedTextColor == null) model.titleFocusedTextColor = getColor(R.color.focused_form_title)
            if (model.parentFilledColor == null) model.parentFilledColor = getColor(android.R.color.white)
            if (model.parentEmptyColor == null) model.parentEmptyColor = getColor(R.color.lightGray)
            if (model.titleDisabledColor == null) model.titleDisabledColor = model.baseLineColor

            // Set other views in formElement
            model.baseLineView = baseLine
            model.leftIconView = leftIcon
            model.inputCardView = inputParentLayout
            model.inputParentView = inputParentLayout2

            // Set up left icon
            model.leftIconDrawable?.let {
                leftIcon.setImageDrawable(it)
            }
            if (model.leftIconDrawable == null || model.displayLeftIcon == false) leftIcon.visibility = View.GONE

            // Max Lines
            val tempMaxLines = model.maxLines
            model.maxLines = tempMaxLines

            // If an InputType is provided, use it instead
            model.inputType?.let { editTextValue.setRawInputType(it) }

            // If imeOptions are provided, use them instead of actionNext
            model.imeOptions?.let { editTextValue.imeOptions = it }

            editTextValue.setOnFocusChangeListener { _, hasFocus -> model.setLookAccordingToState(hasFocus) }

            editTextValue.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {}

                override fun onTextChanged(charSequence: CharSequence, i: Int, i2: Int, i3: Int) {

                    // get current form element, existing value and new value
                    val currentValue = model.valueAsString
                    val newValue = charSequence.toString()

                    // trigger event only if the value is changed
                    if (currentValue != newValue) {
                        // NOTE: Use setValue()
                        // as this will suppress the unchecked cast
                        model.setValue(newValue)
                        model.error = null
                        formBuilder.onValueChanged(model)
                    }
                }

                override fun afterTextChanged(editable: Editable) {}
            })

            model.enabled = model.enabled == true

        }, object : ViewStateProvider<FormRZTextInputElement, ViewHolder> {
            override fun createViewStateID(model: FormRZTextInputElement): Int {
                return model.id
            }

            override fun createViewState(holder: ViewHolder): ViewState<ViewHolder> {
                return FormRZTextInputViewState(holder)
            }
        })

    private fun setEditTextFocusEnabled(editTextValue: AppCompatEditText, itemView: View) {
        itemView.setOnClickListener {
            editTextValue.requestFocus()
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            editTextValue.setSelection(editTextValue.text?.length ?: 0)
            imm.showSoftInput(editTextValue, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun getColor(colorResId: Int): Int {
        return ContextCompat.getColor(context, colorResId)
    }
}