package io.roadzen.lithotestapp.kform.radiogroup

import com.github.vivchar.rendererrecyclerviewadapter.ViewHolder
import com.thejuki.kformmaster.state.BaseFormViewState

class FormRZRadioGroupViewState(holder: ViewHolder) : BaseFormViewState(holder) {
    private var value: String? = null

    init {

    }

    override fun restore(holder: ViewHolder) {
        super.restore(holder)
    }
}