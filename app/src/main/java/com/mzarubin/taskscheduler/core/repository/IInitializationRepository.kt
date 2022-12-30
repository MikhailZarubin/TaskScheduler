package com.mzarubin.taskscheduler.core.repository

interface IInitializationRepository {
    fun isFirstLaunchApplication(): Boolean
    fun isUserAuthorized(): Boolean
}