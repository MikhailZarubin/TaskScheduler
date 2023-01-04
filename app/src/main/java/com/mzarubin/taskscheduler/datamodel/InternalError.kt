package com.mzarubin.taskscheduler.datamodel

enum class InternalError(val description: String) {
    IS_NOT_CHECKED_EXIST("Account existence has not been checked"),
    SAME_LOGIN_FOUND("Account with the same login was found"),
    USER_NOT_FOUND("There is no account with this id")
}