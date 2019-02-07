package io.roadzen.lithotestapp.model.formfields.dependencies.effectvisibility

import com.google.gson.annotations.SerializedName
import io.roadzen.lithotestapp.model.formfields.dependencies.BaseDependency

abstract class VisibilityDependency : BaseDependency() {


    @SerializedName("condition_met")
    var conditionMet: String? = null

    @SerializedName("condition_not_met")
    var conditionNotMet: String? = null
}