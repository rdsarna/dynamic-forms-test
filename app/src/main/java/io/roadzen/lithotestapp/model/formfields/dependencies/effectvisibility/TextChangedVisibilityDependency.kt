package io.roadzen.lithotestapp.model.formfields.dependencies.effectvisibility

import com.google.gson.annotations.SerializedName

class TextChangedVisibilityDependency : VisibilityDependency() {

    @SerializedName("look_for")
    var lookFor: String? = null
}