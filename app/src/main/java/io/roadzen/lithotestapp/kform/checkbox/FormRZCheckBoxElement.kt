package io.roadzen.lithotestapp.kform.checkbox

import android.view.View
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatCheckBox
import com.thejuki.kformmaster.model.BaseFormElement

/**
 * Form CheckBox Element
 *
 * Form element for AppCompatCheckBox
 *
 * @author **TheJuki** ([GitHub](https://github.com/TheJuki))
 * @version 1.0
 */
open class FormRZCheckBoxElement<T>(tag: Int = -1) : BaseFormElement<T>(tag) {

    @ColorInt
    var titleDisabledColor: Int? = null

    override fun clear() {
        this.value = unCheckedValue
        (this.editView as? AppCompatCheckBox)?.isChecked = false
    }

    /**
     * Sets value to this when checked
     */
    var checkedValue: T? = null

    /**
     * Sets value to this when unchecked
     */
    var unCheckedValue: T? = null

    /**
     * Indicates if the checkbox should be checked
     */
    fun isChecked(): Boolean {
        if (checkedValue == null || value == null)
            return false
        return checkedValue == value
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