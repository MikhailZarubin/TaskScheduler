package com.mzarubin.taskscheduler.core.repository

interface IInitializationRepository {
    suspend fun isFirstLaunchApplication(): Boolean
    suspend fun isUserAuthorized(): Boolean
    suspend fun clearFirstLaunchState()
}