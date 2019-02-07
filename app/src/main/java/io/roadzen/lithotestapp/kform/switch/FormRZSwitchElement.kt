package io.roadzen.lithotestapp.kform.switch

import androidx.annotation.ColorInt
import androidx.appcompat.widget.SwitchCompat
import com.thejuki.kformmaster.model.BaseFormElement

/**
 * Form Switch Element
 *
 * Form element for Switch
 *
 * @author **TheJuki** ([GitHub](https://github.com/TheJuki))
 * @version 1.0
 */
open class FormRZSwitchElement<T>(tag: Int = -1) : BaseFormElement<T>(tag) {

    @ColorInt
    var titleDisabledColor: Int? = null

    override fun clear() {
        this.value = offValue
        (this.editView as? SwitchCompat)?.isChecked = false
    }

    /**
     * Sets value to this when on
     */
    var onValue: T? = null

    /**
     * Sets value to this when off
     */
    var offValue: T? = null

    /**
     * Indicates if the switch should be on
     */
    fun isOn(): Boolean {
        if (onValue == null || value == null)
            return false
        return onValue == value
    }

    override fun onEnabled(enable: Boolean) {
        if (enable) {
            titleTextColor?.let { titleView?.setTextColor(it) }
        } else {
            titleDisabledColor?.let {
                titleView?.setTextColor(it)
            }
        }
    }
}