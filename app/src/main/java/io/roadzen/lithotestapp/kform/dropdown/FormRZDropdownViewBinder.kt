package io.roadzen.lithotestapp.kform.dropdown

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.AdapterView
import android.widget.LinearLayout
import android.widget.Spinner
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder
import com.github.vivchar.rendererrecyclerviewadapter.ViewState
import com.github.vivchar.rendererrecyclerviewadapter.ViewStateProvider
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.thejuki.kformmaster.view.BaseFormViewBinder
import io.roadzen.lithotestapp.R
import io.roadzen.lithotestapp.kform.FormSpinner

class FormRZDropdownViewBinder(private val context: Context,
                               private val formBuilder: FormBuildHelper
) : BaseFormViewBinder() {
    var viewBinder = ViewBinder(
        R.layout.rz_form_element_dropdown,
        FormRZDropdownElement::class.java, { model, finder, _ ->
            val textViewError = finder.find(R.id.formElementError) as AppCompatTextView
            val mainViewLayout = finder.find(R.id.formElementMainLayout) as LinearLayout
            val textViewTitle = finder.find(R.id.formElementTitle) as AppCompatTextView
            val inputParentLayout = finder.find(R.id.formElementInputParentLayout) as CardView
            val baseLine = finder.find(R.id.formElementBaseLine) as View
            val itemView = finder.getRootView() as View
            baseSetup(formElement = model, textViewTitle = textViewTitle, textViewError = textViewError, itemView = itemView,
                dividerView = null, mainViewLayout = mainViewLayout)

            // Set up Spinner
            val spinner = finder.find(R.id.formElementValue) as FormSpinner
            model.editView = spinner
            model.value?.let {
                val index = model.options?.indexOf(it) ?: 0
                spinner.setSelection(index)
            }

            // Set all the default colors
            if (model.titleTextColor == null) model.titleTextColor = getColor(android.R.color.black)
            if (model.errorTextColor == null) model.errorTextColor = getColor(com.thejuki.kformmaster.R.color.colorFormMasterElementErrorTitle)

            // Set other views in formElement
            model.baseLineView = baseLine

//            // Set other views in formElement
//            model.leftIconView = leftIcon
//
//            // Set up left icon
//            model.leftIconDrawable?.let {
//                leftIcon.setImageDrawable(it)
//            }
//            if (model.leftIconDrawable == null || model.displayLeftIcon == false) leftIcon.visibility = View.GONE

            spinner.setSpinnerEventsListener(object : FormSpinner.OnSpinnerEventsListener {
                override fun onSpinnerOpened(spinner: Spinner) {
                    textViewTitle.setTextColor(getColor(R.color.focused_form_title))
                    baseLine.setBackgroundColor(getColor(R.color.focused_form_title))
                    inputParentLayout.cardElevation = 16f
                }

                override fun onSpinnerClosed(spinner: Spinner) {
                    textViewTitle.setTextColor(getColor(android.R.color.black))
                    baseLine.setBackgroundColor(getColor(android.R.color.darker_gray))
                    inputParentLayout.cardElevation = 0f
                    spinner.background = if (model.value != null) {
                        getDrawable(R.drawable.bg_form_spinner_item_selected)
                    } else {
                        getDrawable(R.drawable.bg_form_spinner_item_unselected)
                    }
                }
            })

            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                    model.error = null
                    if (position == 0) {
                        model.setValue(null)
                        spinner.background = getDrawable(R.drawable.bg_form_spinner_item_unselected)
                    } else {
                        model.setValue(model.options?.get(position))
                        spinner.background = getDrawable(R.drawable.bg_form_spinner_item_selected)
                    }
                }
            }

            model.reInitDropdown()

        }, object : ViewStateProvider<FormRZDropdownElement<*>, ViewHolder> {
            override fun createViewStateID(model: FormRZDropdownElement<*>): Int {
                return model.id
            }

            override fun createViewState(holder: ViewHolder): ViewState<ViewHolder> {
                return FormRZDropdownViewState(holder)
            }
        })

    private fun getColor(colorResId: Int): Int {
        return ContextCompat.getColor(context, colorResId)
    }

    private fun getDrawable(drawableRes: Int): Drawable? {
        return ContextCompat.getDrawable(context, drawableRes)
    }
}