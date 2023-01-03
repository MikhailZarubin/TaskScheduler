package com.mzarubin.taskscheduler.core.repository

import com.mzarubin.taskscheduler.util.TaskSchedulerThrowable

interface IAccountRepository {
    suspend fun isAccountExist(accountId: String): Boolean
    @Throws(TaskSchedulerThrowable::class)
    suspend fun createAccount(accountId: String, password: String)
    @Throws(TaskSchedulerThrowable::class)
    suspend fun authorization(encodedPassword: String): String?
}