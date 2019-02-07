package io.roadzen.lithotestapp.kform.textinput

import androidx.appcompat.widget.AppCompatEditText
import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder
import com.thejuki.kformmaster.state.BaseFormViewState
import io.roadzen.lithotestapp.R

class FormRZTextInputViewState(holder: ViewHolder) : BaseFormViewState(holder) {
    private var value: String? = null

    init {
        val editText = holder.viewFinder.find(R.id.formElementValue) as AppCompatEditText
        value = editText.text.toString()
    }

    override fun restore(holder: ViewHolder) {
        super.restore(holder)
        holder.viewFinder.setText(R.id.formElementValue, value)
    }
}