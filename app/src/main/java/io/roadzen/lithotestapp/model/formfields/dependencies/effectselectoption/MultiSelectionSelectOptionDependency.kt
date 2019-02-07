package io.roadzen.lithotestapp.model.formfields.dependencies.effectselectoption

import com.google.gson.annotations.SerializedName

class MultiSelectionSelectOptionDependency : SelectOptionDependency() {

    @SerializedName("look_for")
    var lookFor: List<Int>? = null
}