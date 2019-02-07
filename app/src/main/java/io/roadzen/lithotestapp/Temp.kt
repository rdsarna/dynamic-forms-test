package io.roadzen.lithotestapp

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import io.roadzen.lithotestapp.model.formfields.*
import io.roadzen.lithotestapp.model.formfields.Constants.*
import io.roadzen.lithotestapp.model.formfields.dependencies.BaseDependency
import io.roadzen.lithotestapp.model.formfields.dependencies.effectentertext.AutocompleteEnterTextDependency
import io.roadzen.lithotestapp.model.formfields.dependencies.effectentertext.MultiSelectionEnterTextDependency
import io.roadzen.lithotestapp.model.formfields.dependencies.effectentertext.SingleSelectionEnterTextDependency
import io.roadzen.lithotestapp.model.formfields.dependencies.effectentertext.TextChangedEnterTextDependency
import io.roadzen.lithotestapp.model.formfields.dependencies.effectselectoption.AutocompleteSelectOptionDependency
import io.roadzen.lithotestapp.model.formfields.dependencies.effectselectoption.MultiSelectionSelectOptionDependency
import io.roadzen.lithotestapp.model.formfields.dependencies.effectselectoption.SingleSelectionSelectOptionDependency
import io.roadzen.lithotestapp.model.formfields.dependencies.effectselectoption.TextChangedSelectOptionDependency
import io.roadzen.lithotestapp.model.formfields.dependencies.effectvisibility.*

val gson: Gson
    get() {
        val adapterFactory = RuntimeTypeAdapterFactory.of(BaseFormField::class.java, "type")
            .registerSubtype(AutocompleteFormField::class.java, AUTOCOMPLETE.value)
            .registerSubtype(DateFormField::class.java, DATE.value)
            .registerSubtype(DropdownFormField::class.java, DROPDOWN.value)
            .registerSubtype(HeaderFormField::class.java, HEADER.value)
            .registerSubtype(RadiogroupFormField::class.java, RADIOGROUP.value)
            .registerSubtype(RegexTextFormField::class.java, REGEX_TEXT.value)
            .registerSubtype(SwitchFormField::class.java, SWITCH.value)
            .registerSubtype(TextFormField::class.java, TEXT.value)
            .registerSubtype(TimeFormField::class.java, TIME.value)
            .registerSubtype(ButtonFormField::class.java, BUTTON.value)

        val dependencyAdapterFactory = RuntimeTypeAdapterFactory.of(BaseDependency::class.java, "type")
            .registerSubtype(AutocompleteEnableDependency::class.java, "${AUTOCOMPLETE.value}/${ENABLE.value}")
            .registerSubtype(MultiSelectionEnableDependency::class.java, "${MULTI_SELECTION.value}/${ENABLE.value}")
            .registerSubtype(SingleSelectionEnableDependency::class.java, "${SINGLE_SELECTION.value}/${ENABLE.value}")
            .registerSubtype(TextChangedEnableDependency::class.java, "${TEXT_CHANGED.value}/${ENABLE.value}")
            .registerSubtype(AutocompleteEnterTextDependency::class.java, "${AUTOCOMPLETE.value}/${ENTER_TEXT.value}")
            .registerSubtype(MultiSelectionEnterTextDependency::class.java, "${MULTI_SELECTION.value}/${ENTER_TEXT.value}")
            .registerSubtype(SingleSelectionEnterTextDependency::class.java, "${SINGLE_SELECTION.value}/${ENTER_TEXT.value}")
            .registerSubtype(TextChangedEnterTextDependency::class.java, "${TEXT_CHANGED.value}/${ENTER_TEXT.value}")
            .registerSubtype(AutocompleteSelectOptionDependency::class.java, "${AUTOCOMPLETE.value}/${SELECT_OPTION.value}")
            .registerSubtype(MultiSelectionSelectOptionDependency::class.java, "${MULTI_SELECTION.value}/${SELECT_OPTION.value}")
            .registerSubtype(SingleSelectionSelectOptionDependency::class.java, "${SINGLE_SELECTION.value}/${SELECT_OPTION.value}")
            .registerSubtype(TextChangedSelectOptionDependency::class.java, "${TEXT_CHANGED.value}/${SELECT_OPTION.value}")
            .registerSubtype(AutocompleteVisibilityDependency::class.java, "${AUTOCOMPLETE.value}/${VISIBILITY.value}")
            .registerSubtype(MultiSelectionVisibilityDependency::class.java, "${MULTI_SELECTION.value}/${VISIBILITY.value}")
            .registerSubtype(SingleSelectionVisibilityDependency::class.java, "${SINGLE_SELECTION.value}/${VISIBILITY.value}")
            .registerSubtype(TextChangedVisibilityDependency::class.java, "${TEXT_CHANGED.value}/${VISIBILITY.value}")


        return GsonBuilder()
            .registerTypeAdapterFactory(adapterFactory)
            .registerTypeAdapterFactory(dependencyAdapterFactory)
            .create()
    }

val json = "{\n" +
        "  \"form\": \"claim_intimation\",\n" +
        "  \"fields\": [\n" +
        "    {\n" +
        "      \"id\": \"intim_00\",\n" +
        "      \"type\": \"header\",\n" +
        "      \"title\": \"PERSONAL DETAILS\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": \"intim_01\",\n" +
        "      \"type\": \"text\",\n" +
        "      \"subtype\": \"all_caps\",\n" +
        "      \"title\": \"Policy Holder's Name\",\n" +
        "      \"required\": false,\n" +
        "      \"value\": null\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": \"intim_02\",\n" +
        "      \"type\": \"text\",\n" +
        "      \"subtype\": \"phone\",\n" +
        "      \"max_length\": 10,\n" +
        "      \"title\": \"Contact Number\",\n" +
        "      \"required\": true,\n" +
        "      \"value\": null\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": \"intim_03\",\n" +
        "      \"type\": \"header\",\n" +
        "      \"title\": \"INSPECTION LOCATION\"\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": \"intim_04\",\n" +
        "      \"type\": \"radiogroup\",\n" +
        "      \"title\": null,\n" +
        "      \"options\": [\n" +
        "        \"Registered Address\",\n" +
        "        \"Garage\",\n" +
        "        \"Other\"\n" +
        "      ],\n" +
        "      \"default\": 0,\n" +
        "      \"value\": null,\n" +
        "      \"triggers\": [\n" +
        "        \"single_selection\"\n" +
        "      ]\n" +
        "    },\n" +
        "    {\n" +
        "      \"id\": \"intim_05\",\n" +
        "      \"type\": \"text\",\n" +
        "      \"subtype\": \"multiline\",\n" +
        "      \"title\": \"Enter address\",\n" +
        "      \"value\": null,\n" +
        "      \"dependencies\": [\n" +
        "        {\n" +
        "          \"type\": \"single_selection/visibility\",\n" +
        "\t        \"master\": \"intim_04\",\n" +
        "\t        \"trigger\": \"single_selection\",\n" +
        "\t        \"effect\": \"visibility\",\n" +
        "\t        \"look_for\": [ 0, 2 ],\n" +
        "\t        \"condition_met\": \"visible\",\n" +
        "\t        \"condition_not_met\": \"gone\"\n" +
        "        }\n" +
        "      ]\n" +
        "    }\n" +
        "  ]\n" +
        "}"