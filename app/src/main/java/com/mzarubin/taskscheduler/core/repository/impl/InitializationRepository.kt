package com.mzarubin.taskscheduler.core.repository.impl

import android.content.SharedPreferences
import com.mzarubin.taskscheduler.core.repository.IInitializationRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InitializationRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences?
) : IInitializationRepository {
    override fun isFirstLaunchApplication(): Boolean {
        return true
    }

    override fun isUserAuthorized(): Boolean {
        return true
    }
}