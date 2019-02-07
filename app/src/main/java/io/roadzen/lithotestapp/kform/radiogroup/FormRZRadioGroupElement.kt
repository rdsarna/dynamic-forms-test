package io.roadzen.lithotestapp.kform.radiogroup

import android.view.LayoutInflater
import android.widget.RadioButton
import android.widget.TableRow
import androidx.core.view.ViewCompat
import com.thejuki.kformmaster.model.BaseFormElement
import io.roadzen.lithotestapp.R
import io.roadzen.lithotestapp.ToggleButtonTableLayout

class FormRZRadioGroupElement<T>(tag: Int = -1) : BaseFormElement<T>(tag) {

    override fun clear() {
        this.value = null
        (this.editView as? ToggleButtonTableLayout)?.clear()
    }

    /**
     * Set max radio buttons in one row
     */
    var maxInRow: Int = 2

//    /**
//     * Drawable Direction
//     */
//    enum class DrawableDirection {
//        Left,
//        Right,
//        Top,
//        Bottom,
//        Center
//    }
//
//    /**
//     * Drawable direction of the drawable in [SegmentedDrawable]
//     */
//    var drawableDirection: DrawableDirection = DrawableDirection.Top

    /**
     * Disable to stack the radio buttons vertically
     */
    var horizontal: Boolean = true

    /**
     * Enable to fill the whole width
     */
    var fillSpace: Boolean = true

    /**
     * Form Element Options
     */
    var options: List<T>? = null
        set(value) {
            field = value
            reInitGroup()
        }

//    /**
//     * Margin for each radio button (stroke)
//     */
//    var marginDp: Int? = null
//        set(value) {
//            field = value
//            editView?.let {
//                if (it is SegmentedGroup && value != null) {
//                    it.setMarginDp(value)
//                }
//            }
//        }

//    /**
//     * Tint color for each radio button
//     */
//    @ColorInt
//    var tintColor: Int? = null
//        set(value) {
//            field = value
//            editView?.let {
//                if (it is SegmentedGroup && value != null) {
//                    it.setTintColor(value)
//                }
//            }
//        }

//    /**
//     * Unchecked tint color for each radio button
//     */
//    @ColorInt
//    var unCheckedTintColor: Int? = null
//        set(value) {
//            field = value
//            editView?.let {
//                if (it is SegmentedGroup && value != null) {
//                    it.setUnCheckedTintColor(value)
//                }
//            }
//        }

//    /**
//     * Checked text color for each radio button
//     */
//    @ColorInt
//    var checkedTextColor: Int? = null
//        set(value) {
//            field = value
//            editView?.let {
//                if (it is SegmentedGroup && value != null) {
//                    it.setCheckedTextColor(value)
//                }
//            }
//        }

//    /**
//     * Text Size (In SP) for each radio button
//     */
//    var textSize: Float? = null
//        set(value) {
//            field = value
//            editView?.let {
//                if (it is SegmentedGroup && value != null) {
//                    it.setTextSize(value)
//                }
//            }
//        }

//    /**
//     * Padding for each radio button
//     */
//    var padding: Int? = null
//        set(value) {
//            field = value
//            editView?.let {
//                if (it is SegmentedGroup && value != null) {
//                    it.setPadding(value)
//                }
//            }
//        }

    /**
     * Re-initializes the group
     * Should be called after the options list changes
     */
    fun reInitGroup() {
        editView?.let {
            if (it is ToggleButtonTableLayout) {
                it.removeAllViews()

                var tableRow = TableRow(it.context)
                options?.forEach { item ->
                    val rb = LayoutInflater.from(it.context).inflate(R.layout.rz_form_element_radio_button, null) as RadioButton
                    rb.text = item.toString()
                    rb.id = ViewCompat.generateViewId()
                    rb.isChecked = item == this@FormRZRadioGroupElement.value
                    rb.layoutParams = TableRow.LayoutParams(0, TableRow.LayoutParams.WRAP_CONTENT, 1f)

                    if (tableRow.childCount == maxInRow) {
                        it.addView(tableRow)
                        tableRow = TableRow(it.context)
                    }
                    tableRow.addView(rb)
                }
                it.addView(tableRow)
            }
        }
    }
}
