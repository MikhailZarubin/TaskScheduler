package com.mzarubin.taskscheduler.core.repository

import com.mzarubin.taskscheduler.datamodel.UserInfoDataModel
import com.mzarubin.taskscheduler.util.TaskSchedulerThrowable

interface IUserDataRepository {
    suspend fun createUser(userId: String, name: String, surname: String)

    @Throws(TaskSchedulerThrowable::class)
    suspend fun getUser(userId: String): UserInfoDataModel
}