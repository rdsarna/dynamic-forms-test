package io.roadzen.lithotestapp

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.CompoundButton
import android.widget.TableLayout
import android.widget.TableRow



class ToggleButtonTableLayout : TableLayout {

    private val buttons: MutableList<CompoundButton> = mutableListOf()
    private var lastChecked: CompoundButton? = null
    private var listener: ((Int, Boolean) -> Unit)? = null

    val checkedButton: Int
        get() = lastChecked?.id ?: -1

    /**
     * @param context
     */
    constructor(context: Context) : super(context)

    /**
     * @param context
     * @param attrs
     */
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun addView(
        child: View, index: Int,
        params: android.view.ViewGroup.LayoutParams
    ) {
        super.addView(child, index, params)
        setChildrenOnClickListener(child as TableRow)
        addButtons(child)
    }

    override fun addView(child: View, params: android.view.ViewGroup.LayoutParams) {
        super.addView(child, params)
        setChildrenOnClickListener(child as TableRow)
        addButtons(child)
    }

    private fun addButtons(tableRow: TableRow) {
        for (i in 0 until tableRow.childCount) {
            val child = tableRow.getChildAt(i) as CompoundButton
            buttons.add(child)
        }
    }

    private fun setChildrenOnClickListener(tableRow: TableRow) {
        for (i in 0 until tableRow.childCount) {
            val v = tableRow.getChildAt(i)
            if (v is CompoundButton) {
                v.setOnClickListener { setChecked(v) }
                if (v.isChecked) lastChecked = v
            }
        }
    }

    private fun setChecked(button: CompoundButton) {
        if (button == lastChecked) return

        for (btn in buttons) {
            btn.isChecked = false
        }

        button.isChecked = true

        lastChecked = button

        listener?.invoke(buttons.indexOf(button), button.isChecked)
    }

    fun clear() {
        for (button in buttons) {
            button.isChecked = false
        }
    }

    fun setOnCheckedChangeListener(listener: ((Int, Boolean) -> Unit)?) {
        this.listener = listener
    }
}