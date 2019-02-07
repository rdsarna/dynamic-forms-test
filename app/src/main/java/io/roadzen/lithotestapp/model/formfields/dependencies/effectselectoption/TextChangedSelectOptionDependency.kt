package io.roadzen.lithotestapp.model.formfields.dependencies.effectselectoption

import com.google.gson.annotations.SerializedName

class TextChangedSelectOptionDependency : SelectOptionDependency() {

    @SerializedName("look_for")
    var lookFor: String? = null
}