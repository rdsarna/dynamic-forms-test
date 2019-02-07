package io.roadzen.lithotestapp.model.formfields.dependencies.effectentertext

import com.google.gson.annotations.SerializedName
import io.roadzen.lithotestapp.model.formfields.dependencies.BaseDependency

class TextChangedEnterTextDependency : BaseDependency() {

    @SerializedName("look_for")
    var lookFor: String? = null

    @SerializedName("condition_met")
    var conditionMet: String? = null
}