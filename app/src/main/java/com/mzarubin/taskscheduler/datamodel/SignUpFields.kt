package com.mzarubin.taskscheduler.datamodel

import android.text.InputType
import com.mzarubin.taskscheduler.R

enum class SignUpFields(val headerId: Int, val inputType: Int? = null) {
    NAME(R.string.field_name),
    SURNAME(R.string.field_surname),
    LOGIN(R.string.field_login),
    PASSWORD(R.string.field_password,
        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD),
    PASSWORD_REPEAT(R.string.field_repeat_password,
        InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
}