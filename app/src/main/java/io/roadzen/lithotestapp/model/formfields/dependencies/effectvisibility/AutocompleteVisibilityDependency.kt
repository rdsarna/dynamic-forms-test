package io.roadzen.lithotestapp.model.formfields.dependencies.effectvisibility

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class AutocompleteVisibilityDependency : VisibilityDependency() {

    @SerializedName("look_for")
    var lookFor: JsonObject? = null
}