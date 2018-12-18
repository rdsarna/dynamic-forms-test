package io.roadzen.lithotestapp

import io.roadzen.lithotestapp.formfields.BaseFormField
import io.roadzen.lithotestapp.formfields.DropdownFormField
import io.roadzen.lithotestapp.formfields.SwitchFormField
import io.roadzen.lithotestapp.formfields.TextFormField

fun getQuestions(): List<BaseFormField> {
    val questionList = mutableListOf<BaseFormField>()
    questionList.add(
        TextFormField(
            id = 0,
            title = "Name",
            errorMsg = null,
            required = true,
            enabled = true,
            hint = "Enter Your Name",
            value = null
        )
    )
    questionList.add(
        TextFormField(
            id = 1,
            title = "Father's Name",
            errorMsg = null,
            required = false,
            enabled = true,
            hint = "Enter Your Father's Name",
            value = null
        )
    )
    questionList.add(
        DropdownFormField(
            id = 2,
            title = "Gender",
            errorMsg = null,
            required = true,
            enabled = true,
            options = listOf("Male", "Female", "Other"),
            chosenValue = 0,
            hint = "Pick One"
        )
    )
    questionList.add(
        TextFormField(
            id = 3,
            title = "Test Thing Field",
            errorMsg = null,
            required = true,
            enabled = true,
            hint = "This is a test",
            value = null
        )
    )
    questionList.add(
        SwitchFormField(
            id = 4,
            title = "Are you cool?",
            errorMsg = null,
            required = true,
            enabled = true,
            value = false,
            isMaster = true
        )
    )
    questionList.add(
        TextFormField(
            id = 5,
            title = "Test Thing Field",
            errorMsg = null,
            required = true,
            enabled = true,
            hint = "This is a test",
            value = null,
            dependantOn = 4,
            dependantValue = "true"
        )
    )
    questionList.add(
        DropdownFormField(
            id = 6,
            title = "Gender",
            errorMsg = null,
            required = true,
            enabled = true,
            options = listOf("Male", "Female", "Other"),
            chosenValue = 0,
            hint = "Pick One",
            dependantOn = 4,
            dependantValue = "true"
        )
    )
    questionList.add(
        TextFormField(
            id = 7,
            title = "Test Thing Field",
            errorMsg = null,
            required = true,
            enabled = true,
            hint = "This is a test",
            value = null,
            dependantOn = 4,
            dependantValue = "true"
        )
    )
    questionList.add(
        TextFormField(
            id = 8,
            title = "Test Thing Field",
            errorMsg = null,
            required = true,
            enabled = true,
            hint = "This is a test",
            value = null,
            dependantOn = 4,
            dependantValue = "true"
        )
    )
    questionList.add(
        DropdownFormField(
            id = 9,
            title = "Gender",
            errorMsg = null,
            required = true,
            enabled = true,
            options = listOf("Male", "Female", "Other"),
            chosenValue = 0,
            hint = "Pick One"
        )
    )
    questionList.add(
        TextFormField(
            id = 10,
            title = "Name",
            errorMsg = null,
            required = true,
            enabled = true,
            hint = "Enter Your Name",
            value = null
        )
    )
    questionList.add(
        TextFormField(
            id = 11,
            title = "Name",
            errorMsg = null,
            required = true,
            enabled = true,
            hint = "Enter Your Name",
            value = null
        )
    )
    questionList.add(
        TextFormField(
            id = 12,
            title = "Name",
            errorMsg = null,
            required = true,
            enabled = true,
            hint = "Enter Your Name",
            value = null
        )
    )
    for (i in 13..100) {
        questionList.add(
            TextFormField(
                id = i,
                title = "Question No. $i",
                errorMsg = null,
                required = true,
                enabled = true,
                hint = "Testing Hint",
                value = null
            )
        )
    }

    return questionList
}