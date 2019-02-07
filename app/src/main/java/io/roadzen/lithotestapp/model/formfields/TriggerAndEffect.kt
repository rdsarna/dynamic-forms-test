package io.roadzen.lithotestapp.model.formfields

enum class TriggerAndEffect(val value: String) {

    // Triggers
    TEXT_CHANGED("text_changed"),
    SINGLE_SELECTION_MADE("single_selection_made"),
    MULTI_SELECTION_MADE("multi_selection_made"),
    AUTOCOMPLETE_SELECTED("autocomplete_selected"),

    // Effects
    VISIBILITY("visibility"),
    ENTER_TEXT("enter_text"),
    SELECT_OPTION("select_option"),
    ENABLE_DISABLE("enable_disable")
}