package io.roadzen.lithotestapp.kform.switch

import androidx.appcompat.widget.SwitchCompat
import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder
import com.thejuki.kformmaster.R
import com.thejuki.kformmaster.state.BaseFormViewState

/**
 * Form Switch ViewState
 *
 * View State for [FormRZSwitchElement]
 *
 * @author **TheJuki** ([GitHub](https://github.com/TheJuki))
 * @version 1.0
 */
class FormRZSwitchViewState(holder: ViewHolder) : BaseFormViewState(holder) {
    private var value: Boolean? = null

    init {
        val switch = holder.viewFinder.find(R.id.formElementValue) as SwitchCompat
        value = switch.isChecked
    }

    override fun restore(holder: ViewHolder) {
        super.restore(holder)
        holder.viewFinder.setChecked(R.id.formElementValue, value ?: false)
    }
}