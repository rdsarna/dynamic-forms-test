package io.roadzen.lithotestapp.kform.dropdown

import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.annotation.ColorInt
import com.thejuki.kformmaster.model.BaseFormElement
import io.roadzen.lithotestapp.R

class FormRZDropdownElement<T>(tag: Int = -1) : BaseFormElement<T>(tag) {

    /**
     * Form Element Options
     */
    var options: List<T>? = null
        set(value) {
            field = value
            reInitDropdown()
        }

    var baseLineView: View? = null

    @ColorInt
    var baseLineColor: Int? = null

    override fun onErrorChanged() {
        if (error == null) {
            titleTextColor?.let {
                titleView?.setTextColor(it)
            }
            baseLineColor?.let {
                baseLineView?.setBackgroundColor(it)
            }
        } else {
            errorTextColor?.let {
                titleView?.setTextColor(it)
                baseLineView?.setBackgroundColor(it)
            }
        }
    }

    fun reInitDropdown() {
        editView?.let {
            val spinnerView = it as Spinner

            if (options?.isEmpty() == true) spinnerView.isEnabled = false
            else {
                spinnerView.adapter =
                    ArrayAdapter<T>(it.context, R.layout.rz_form_spinner_item, options?.toMutableList())
            }
        }
    }
}