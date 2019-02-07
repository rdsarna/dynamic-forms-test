package io.roadzen.lithotestapp.kform.checkbox

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder
import com.github.vivchar.rendererrecyclerviewadapter.ViewState
import com.github.vivchar.rendererrecyclerviewadapter.ViewStateProvider
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.thejuki.kformmaster.view.BaseFormViewBinder
import io.roadzen.lithotestapp.R

/**
 * Form CheckBox Binder
 *
 * View Binder for [FormRZCheckBoxElement]
 *
 * @author **TheJuki** ([GitHub](https://github.com/TheJuki))
 * @version 1.0
 */
class FormRZCheckBoxViewBinder(
    private val context: Context,
    private val formBuilder: FormBuildHelper,
    @LayoutRes private val layoutID: Int? = null
) : BaseFormViewBinder() {
    val viewBinder = ViewBinder(layoutID
            ?: R.layout.rz_form_element_checkbox, FormRZCheckBoxElement::class.java, { model, finder, _ ->
        val textViewTitle = finder.find(R.id.formElementTitle) as? AppCompatTextView
        val mainViewLayout = finder.find(R.id.formElementMainLayout) as? LinearLayout
        val textViewError = finder.find(R.id.formElementError) as? AppCompatTextView
        val dividerView = finder.find(R.id.formElementDivider) as? View
        val itemView = finder.getRootView() as View
        baseSetup(model, dividerView, textViewTitle, textViewError, itemView, null)

        val checkBox = finder.find(R.id.formElementValue) as AppCompatCheckBox
        checkBox.isChecked = model.isChecked()

        model.editView = checkBox
        model.value?.let { checkBox.isChecked = it == model.checkedValue }
        model.titleDisabledColor = ContextCompat.getColor(context, android.R.color.darker_gray)

        // Delay setting to make sure editView is set first
        model.mainLayoutView = mainViewLayout

        setCheckBoxFocusEnabled(itemView, checkBox)

        checkBox.setOnCheckedChangeListener { _, isChecked ->
            model.error = null
            if (isChecked) {
                model.setValue(model.checkedValue)
            } else {
                model.setValue(model.unCheckedValue)
            }
            formBuilder.onValueChanged(model)
        }

        model.enabled = model.enabled == true
    }, object : ViewStateProvider<FormRZCheckBoxElement<*>, ViewHolder> {
        override fun createViewStateID(model: FormRZCheckBoxElement<*>): Int {
            return model.id
        }

        override fun createViewState(holder: ViewHolder): ViewState<ViewHolder> {
            return FormRZCheckBoxViewState(holder)
        }
    })

    private fun setCheckBoxFocusEnabled(itemView: View, checkBox: AppCompatCheckBox) {
        itemView.setOnClickListener {
            checkBox.isChecked = !checkBox.isChecked
        }
    }
}
