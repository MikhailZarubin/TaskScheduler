package com.mzarubin.taskscheduler.core.repository.impl

import android.content.SharedPreferences
import com.mzarubin.taskscheduler.core.repository.IAccountRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : IAccountRepository