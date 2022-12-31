package com.mzarubin.taskscheduler.core.repository.impl

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.mzarubin.taskscheduler.core.repository.IInitializationRepository
import com.mzarubin.taskscheduler.util.IS_FIRST_LAUNCH_KEY
import com.mzarubin.taskscheduler.util.USER_ID_KEY
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class InitializationRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : IInitializationRepository {
    override suspend fun isFirstLaunchApplication(): Boolean =
        sharedPreferences.getBoolean(IS_FIRST_LAUNCH_KEY, true)

    override suspend fun isUserAuthorized(): Boolean =
        sharedPreferences.getString(USER_ID_KEY, null) != null

    @SuppressLint("CommitPrefEdits")
    override suspend fun clearFirstLaunchState() {
        val editor = sharedPreferences.edit()
        editor?.putBoolean(IS_FIRST_LAUNCH_KEY, false)
        editor?.apply()
    }
}