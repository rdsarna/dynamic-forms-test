package io.roadzen.lithotestapp.model.formfields

enum class Constants(val value: String) {

    ALL_TEXT("all_text"),
    SELECTED_TITLE("selected_title"),
    SELECTED_TITLES("selected_titles"),
    EMPTY(""),

    ENABLE("enable"),
    DISABLE("disable"),
    VISIBLE("visible"),
    GONE("gone"),

    // Types
    HEADER("header"),
    AUTOCOMPLETE("autocomplete"),
    REGEX_TEXT("regex_text"),
    TEXT("text"),
    DROPDOWN("dropdown"),
    RADIOGROUP("radiogroup"),
    DATE("date"),
    TIME("time"),
    SWITCH("switch"),
    BUTTON("button"),

    // Property names
    FIELDS("fields"),
    TYPE("type"),
    TITLE("title"),

    // Triggers
    TEXT_CHANGED("text_changed"),
    SINGLE_SELECTION("single_selection"),
    MULTI_SELECTION("multi_selection"),

    // Effects
    VISIBILITY("visibility"),
    ENTER_TEXT("enter_text"),
    SELECT_OPTION("select_option"),

    // Text subtypes
//    MULTILINE("multiline"),
//    ALL_CAPS("all_caps"),
//    NONE("none"),
//    PHONE("phone"),

    // Dependency Types
    AUTOCOMPLETE_ENABLE("${AUTOCOMPLETE.value}/${ENABLE.value}"),
    MULTISELECTION_ENABLE("${MULTI_SELECTION.value}/${ENABLE.value}"),
    SINGLESELECTION_ENABLE("${SINGLE_SELECTION.value}/${ENABLE.value}"),
    TEXTCHANGED_ENABLE("${TEXT_CHANGED.value}/${ENABLE.value}"),
    AUTOCOMPLETE_ENTERTEXT("${AUTOCOMPLETE.value}/${ENTER_TEXT.value}"),
    MULTISELECTION_ENTERTEXT("${MULTI_SELECTION.value}/${ENTER_TEXT.value}"),
    SINGLESELECTION_ENTERTEXT("${SINGLE_SELECTION.value}/${ENTER_TEXT.value}"),
    TEXTCHANGED_ENTERTEXT("${TEXT_CHANGED.value}/${ENTER_TEXT.value}"),
    AUTOCOMPLETE_SELECTOPTION("${AUTOCOMPLETE.value}/${SELECT_OPTION.value}"),
    MULTISELECTION_SELECTOPTION("${MULTI_SELECTION.value}/${SELECT_OPTION.value}"),
    SINGLESELECTION_SELECTOPTION("${SINGLE_SELECTION.value}/${SELECT_OPTION.value}"),
    TEXTCHANGED_SELECTOPTION("${TEXT_CHANGED.value}/${SELECT_OPTION.value}"),
    AUTOCOMPLETE_VISIBILITY("${AUTOCOMPLETE.value}/${VISIBILITY.value}"),
    MULTISELECTION_VISIBILITY("${MULTI_SELECTION.value}/${VISIBILITY.value}"),
    SINGLESELECTION_VISIBILITY("${SINGLE_SELECTION.value}/${VISIBILITY.value}"),
    TEXTCHANGED_VISIBILITY("${TEXT_CHANGED.value}/${VISIBILITY.value}"),
}