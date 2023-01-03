package com.mzarubin.taskscheduler.core.repository.impl

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.google.gson.Gson
import com.mzarubin.taskscheduler.core.repository.IAccountRepository
import com.mzarubin.taskscheduler.datamodel.AccountPrimaryDataModel
import com.mzarubin.taskscheduler.datamodel.InternalError
import com.mzarubin.taskscheduler.util.PRIMARY_ACCOUNTS_INFO_KEY
import com.mzarubin.taskscheduler.util.TaskSchedulerThrowable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : IAccountRepository {
    private val jsonConverter = Gson()
    private var cachedPrimaryInfo: AccountPrimaryDataModel? = null

    override suspend fun isAccountExist(accountId: String): Boolean {
        initializeCache(accountId)
        return cachedPrimaryInfo != null
    }

    @SuppressLint("MutatingSharedPrefs")
    override suspend fun createAccount(accountId: String, password: String) {
        if (cachedPrimaryInfo != null) {
            throw TaskSchedulerThrowable(InternalError.SAME_LOGIN_FOUND)
        }
        val primaryAccountsInfo =
            sharedPreferences.getStringSet(PRIMARY_ACCOUNTS_INFO_KEY, mutableSetOf())?.apply {
                add(
                    jsonConverter.toJson(
                        AccountPrimaryDataModel(
                            accountId, password
                        )
                    )
                )
            }
        val editor = sharedPreferences.edit()
        editor.putStringSet(PRIMARY_ACCOUNTS_INFO_KEY, primaryAccountsInfo)
        editor.apply()
    }

    override suspend fun authorization(encodedPassword: String): String? {
        if (cachedPrimaryInfo == null) {
            throw TaskSchedulerThrowable(InternalError.IS_NOT_CHECKED_EXIST)
        }
        return if (cachedPrimaryInfo?.password == encodedPassword) {
            cachedPrimaryInfo?.password
        } else {
            null
        }
    }

    private fun initializeCache(accountId: String) {
        val primaryAccountsInfo =
            sharedPreferences.getStringSet(PRIMARY_ACCOUNTS_INFO_KEY, mutableSetOf())
        primaryAccountsInfo?.find {
            jsonConverter.fromJson(
                it,
                AccountPrimaryDataModel::class.java
            ).accountId == accountId
        }?.apply {
            cachedPrimaryInfo = jsonConverter.fromJson(this, AccountPrimaryDataModel::class.java)
        }
    }
}