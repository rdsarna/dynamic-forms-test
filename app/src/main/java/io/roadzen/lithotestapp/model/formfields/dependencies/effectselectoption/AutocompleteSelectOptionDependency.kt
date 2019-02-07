package io.roadzen.lithotestapp.model.formfields.dependencies.effectselectoption

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class AutocompleteSelectOptionDependency : SelectOptionDependency() {

    @SerializedName("look_for")
    var lookFor: JsonObject? = null
}