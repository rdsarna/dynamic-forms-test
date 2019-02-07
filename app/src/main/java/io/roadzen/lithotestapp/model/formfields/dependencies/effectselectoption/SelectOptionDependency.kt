package io.roadzen.lithotestapp.model.formfields.dependencies.effectselectoption

import com.google.gson.annotations.SerializedName
import io.roadzen.lithotestapp.model.formfields.dependencies.BaseDependency

abstract class SelectOptionDependency : BaseDependency() {

    @SerializedName("condition_met")
    var conditionMet: List<Int>? = null

    @SerializedName("condition_not_met")
    var conditionNotMet: List<Int>? = null
}