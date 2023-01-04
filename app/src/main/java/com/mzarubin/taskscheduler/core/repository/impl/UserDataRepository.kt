package com.mzarubin.taskscheduler.core.repository.impl

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.google.gson.Gson
import com.mzarubin.taskscheduler.core.repository.IUserDataRepository
import com.mzarubin.taskscheduler.datamodel.AccountSecondaryDataModel
import com.mzarubin.taskscheduler.datamodel.InternalError
import com.mzarubin.taskscheduler.datamodel.UserInfoDataModel
import com.mzarubin.taskscheduler.util.SECONDARY_ACCOUNTS_INFO_KEY
import com.mzarubin.taskscheduler.util.TaskSchedulerThrowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserDataRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : IUserDataRepository {
    private val jsonConverter = Gson()

    @SuppressLint("MutatingSharedPrefs")
    override suspend fun createUser(userId: String, name: String, surname: String) {
        val secondaryAccountsInfo =
            sharedPreferences.getStringSet(SECONDARY_ACCOUNTS_INFO_KEY, mutableSetOf())?.apply {
                add(
                    jsonConverter.toJson(
                        AccountSecondaryDataModel(
                            userId,
                            name,
                            surname
                        )
                    )
                )
            }
        val editor = sharedPreferences.edit()
        editor.putStringSet(SECONDARY_ACCOUNTS_INFO_KEY, secondaryAccountsInfo)
        editor.apply()
    }

    override suspend fun getUser(userId: String): UserInfoDataModel {
        val userDataJson =
            sharedPreferences.getStringSet(SECONDARY_ACCOUNTS_INFO_KEY, mutableSetOf())?.find {
                jsonConverter.fromJson(
                    it,
                    AccountSecondaryDataModel::class.java
                ).userId == userId
            }
        userDataJson?.let {
            val accountSecondary = jsonConverter.fromJson(
                it,
                AccountSecondaryDataModel::class.java
            )
            return UserInfoDataModel(
                accountSecondary.name,
                accountSecondary.surname
            )
        }
        throw TaskSchedulerThrowable(InternalError.USER_NOT_FOUND)
    }
}