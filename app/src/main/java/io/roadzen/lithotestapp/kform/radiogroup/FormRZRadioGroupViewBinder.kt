package io.roadzen.lithotestapp.kform.radiogroup

import android.content.Context
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.widget.AppCompatTextView
import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder
import com.github.vivchar.rendererrecyclerviewadapter.ViewState
import com.github.vivchar.rendererrecyclerviewadapter.ViewStateProvider
import com.github.vivchar.rendererrecyclerviewadapter.binder.ViewBinder
import com.thejuki.kformmaster.helper.FormBuildHelper
import com.thejuki.kformmaster.view.BaseFormViewBinder
import io.roadzen.lithotestapp.R
import io.roadzen.lithotestapp.ToggleButtonTableLayout

class FormRZRadioGroupViewBinder(private val context: Context, private val formBuilder: FormBuildHelper, @LayoutRes private val layoutID: Int?) : BaseFormViewBinder() {
    val viewBinder = ViewBinder(layoutID
            ?: R.layout.rz_form_element_radiogroup, FormRZRadioGroupElement::class.java, { model, finder, _ ->
        val textViewTitle = finder.find(R.id.formElementTitle) as? AppCompatTextView
        val textViewError = finder.find(R.id.formElementError) as? AppCompatTextView
        val dividerView = finder.find(R.id.formElementDivider) as? View
        val itemView = finder.getRootView() as View
        baseSetup(model, dividerView, textViewTitle, textViewError, itemView, null)

        val toggleButtonTableLayout = finder.find(R.id.formElementValue) as ToggleButtonTableLayout

        model.editView = toggleButtonTableLayout

        toggleButtonTableLayout.setOnCheckedChangeListener { indexOfView, _ ->
            model.error = null
            if (indexOfView == -1) {
                model.setValue(null)
            } else {
                model.setValue(model.options?.get(indexOfView))
            }
            formBuilder.onValueChanged(model)
        }

//        group, checkedId ->
//        if (!segmented.holdup) {
//            segmented.holdup = true
//            val index = group.indexOfChild(group.findViewById(checkedId))
//            model.error = null
//            if (index < 0) {
//                model.setValue(null)
//            } else {
//                model.setValue(model.options?.get(index))
//            }
//            formBuilder.onValueChanged(model)
//        } else {
//            segmented.holdup = false
//        }


        model.reInitGroup()

    }, object : ViewStateProvider<FormRZRadioGroupElement<*>, ViewHolder> {
        override fun createViewStateID(model: FormRZRadioGroupElement<*>): Int {
            return model.id
        }

        override fun createViewState(holder: ViewHolder): ViewState<ViewHolder> {
            return FormRZRadioGroupViewState(holder)
        }
    })
}
