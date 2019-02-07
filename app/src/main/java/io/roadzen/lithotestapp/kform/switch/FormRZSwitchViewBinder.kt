package io.roadzen.lithotestapp.kform.switch

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.SwitchCompat
import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder
import com.github.vivchar.rendererrecyclerviewadapter.ViewState
import com.github.vivchar.rendererrecyclerviewadapter.ViewStateProvider
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.thejuki.kformmaster.view.BaseFormViewBinder
import io.roadzen.lithotestapp.R

/**
 * Form Switch Binder
 *
 * View Binder for [FormRZSwitchElement]
 *
 * @author **TheJuki** ([GitHub](https://github.com/TheJuki))
 * @version 1.0
 */
class FormRZSwitchViewBinder(
    private val context: Context,
    private val formBuilder: FormBuildHelper,
    @LayoutRes private val layoutID: Int? = null
) : BaseFormViewBinder() {
    val viewBinder = ViewBinder(layoutID
            ?: R.layout.rz_form_element_switch, FormRZSwitchElement::class.java, { model, finder, _ ->
        val textViewTitle = finder.find(R.id.formElementTitle) as? AppCompatTextView
        val mainViewLayout = finder.find(R.id.formElementMainLayout) as? LinearLayout
        val textViewError = finder.find(R.id.formElementError) as? AppCompatTextView
        val dividerView = finder.find(R.id.formElementDivider) as? View
        val itemView = finder.getRootView() as View
        baseSetup(model, dividerView, textViewTitle, textViewError, itemView, null)

        val switch = finder.find(R.id.formElementValue) as SwitchCompat
        switch.isChecked = model.isOn()

        model.editView = switch
        model.value?.let { switch.isChecked = it == model.onValue }

        // Delay setting to make sure editView is set first
        model.mainLayoutView = mainViewLayout

        setSwitchFocusEnabled(itemView, switch)

        switch.setOnCheckedChangeListener { _, isChecked ->
            model.error = null
            if (isChecked) {
                model.setValue(model.onValue)
            } else {
                model.setValue(model.offValue)
            }
            formBuilder.onValueChanged(model)
        }

        model.enabled = model.enabled == true
    }, object : ViewStateProvider<FormRZSwitchElement<*>, ViewHolder> {
        override fun createViewStateID(model: FormRZSwitchElement<*>): Int {
            return model.id
        }

        override fun createViewState(holder: ViewHolder): ViewState<ViewHolder> {
            return FormRZSwitchViewState(holder)
        }
    })

    private fun setSwitchFocusEnabled(itemView: View, switch: SwitchCompat) {
        itemView.setOnClickListener {
            switch.isChecked = !switch.isChecked
        }
    }
}
