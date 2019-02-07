package io.roadzen.lithotestapp.model.formfields.dependencies.effectentertext

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName
import io.roadzen.lithotestapp.model.formfields.dependencies.BaseDependency

class AutocompleteEnterTextDependency : BaseDependency() {

    @SerializedName("look_for")
    var lookFor: JsonObject? = null

    @SerializedName("condition_met")
    var conditionMet: List<String>? = null
}