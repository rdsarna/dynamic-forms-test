package io.roadzen.lithotestapp.kform.textinput

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatEditText
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import com.thejuki.kformmaster.R
import com.thejuki.kformmaster.model.BaseFormElement
import kotlinx.android.synthetic.main.rz_form_element_radiogroup.view.*

class FormRZTextInputElement(tag: Int = -1) : BaseFormElement<String>(tag) {

    var baseLineView: View? = null
    @ColorInt
    var baseLineColor: Int? = null
    @ColorInt
    var parentFilledColor: Int? = null
    @ColorInt
    var parentEmptyColor: Int? = null
    @ColorInt
    var titleDisabledColor: Int? = null
    var regex: String? = null
    var displayLeftIcon: Boolean? = null
    var inputCardView: CardView? = null
    var inputParentView: ConstraintLayout? = null
    internal var leftIconView: ImageView? = null

    var leftIconDrawable: Drawable? = null
        set(value) {
            field = value
            leftIconView?.setImageDrawable(value)
        }

    override fun onErrorChanged() {
        if (error == null) {
            setLookAccordingToState(editView?.hasFocus() == true)
        } else {
            errorTextColor?.let {
                titleView?.setTextColor(it)
                baseLineView?.setBackgroundColor(it)
            }
        }
    }

    override val isValid: Boolean
        get() = regex?.let {
            value?.matches(it.toRegex())
        } ?: super.isValid

    override fun onEnabled(enable: Boolean) {
        if (enable) {
            setLookAccordingToState(false)
            baseLineView?.visibility = View.VISIBLE
        } else {
            titleDisabledColor?.let {
                titleView?.setTextColor(it)
//                (editView as? AppCompatEditText)?.setTextColor(it)
            }
            baseLineView?.visibility = View.GONE
            inputParentView?.setBackgroundResource(io.roadzen.lithotestapp.R.drawable.bg_form_text_input)
        }
    }

    internal fun setLookAccordingToState(hasFocus: Boolean) {
        if (hasFocus) {
            titleFocusedTextColor?.let {
                titleView?.setTextColor(it)
                baseLineView?.setBackgroundColor(it)
            }
            inputCardView?.cardElevation = 16f
            parentFilledColor?.let { inputParentView?.setBackgroundColor(it) }
        } else {
            titleTextColor?.let { titleView?.setTextColor(it) }
            baseLineColor?.let { baseLineView?.setBackgroundColor(it) }
            inputCardView?.cardElevation = 0f
            if (value?.isNotEmpty() == true) {
                inputParentView?.setBackgroundResource(io.roadzen.lithotestapp.R.drawable.bg_form_text_input)
            } else {
                parentEmptyColor?.let { inputParentView?.setBackgroundColor(it) }
            }
        }
    }
}