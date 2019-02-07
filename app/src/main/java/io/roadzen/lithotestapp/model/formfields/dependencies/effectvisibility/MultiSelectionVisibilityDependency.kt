package io.roadzen.lithotestapp.model.formfields.dependencies.effectvisibility

import com.google.gson.annotations.SerializedName

class MultiSelectionVisibilityDependency : VisibilityDependency() {

    @SerializedName("look_for")
    var lookFor: List<Int>? = null
}