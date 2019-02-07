package io.roadzen.lithotestapp.kform.checkbox

import androidx.appcompat.widget.AppCompatCheckBox
import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder
import com.thejuki.kformmaster.R
import com.thejuki.kformmaster.state.BaseFormViewState

/**
 * Form CheckBox ViewState
 *
 * View State for [FormRZCheckBoxElement]
 *
 * @author **TheJuki** ([GitHub](https://github.com/TheJuki))
 * @version 1.0
 */
class FormRZCheckBoxViewState(holder: ViewHolder) : BaseFormViewState(holder) {
    private var value: Boolean? = null

    init {
        val checkbox = holder.viewFinder.find(R.id.formElementValue) as AppCompatCheckBox
        value = checkbox.isChecked
    }

    override fun restore(holder: ViewHolder) {
        super.restore(holder)
        holder.viewFinder.setChecked(R.id.formElementValue, value ?: false)
    }
}