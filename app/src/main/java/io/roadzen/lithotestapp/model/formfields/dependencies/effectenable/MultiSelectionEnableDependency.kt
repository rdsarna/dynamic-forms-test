package io.roadzen.lithotestapp.model.formfields.dependencies.effectvisibility

import com.google.gson.annotations.SerializedName
import io.roadzen.lithotestapp.model.formfields.dependencies.BaseDependency

class MultiSelectionEnableDependency : BaseDependency() {

    @SerializedName("look_for")
    var lookFor: List<Int>? = null

    @SerializedName("condition_met")
    var conditionMet: String? = null

    @SerializedName("condition_not_met")
    var conditionNotMet: String? = null
}