package com.mzarubin.taskscheduler.core.repository.impl

import android.annotation.SuppressLint
import android.content.SharedPreferences
import com.google.gson.Gson
import com.mzarubin.taskscheduler.core.repository.IAccountRepository
import com.mzarubin.taskscheduler.datamodel.AccountPrimaryDataModel
import com.mzarubin.taskscheduler.datamodel.InternalError
import com.mzarubin.taskscheduler.util.PRIMARY_ACCOUNTS_INFO_KEY
import com.mzarubin.taskscheduler.util.TaskSchedulerThrowable
import com.mzarubin.taskscheduler.util.USER_ID_KEY
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AccountRepository @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : IAccountRepository {
    private val jsonConverter = Gson()

    private var _cachedPrimaryInfo: AccountPrimaryDataModel? = null
    private val cachedPrimaryInfo: AccountPrimaryDataModel get() = _cachedPrimaryInfo!!

    override suspend fun isAccountExist(accountId: String): Boolean {
        initializeCache(accountId)
        return _cachedPrimaryInfo != null
    }

    @SuppressLint("MutatingSharedPrefs")
    override suspend fun createAccount(accountId: String, password: String) {
        if (_cachedPrimaryInfo != null) {
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
        if (_cachedPrimaryInfo == null) {
            throw TaskSchedulerThrowable(InternalError.IS_NOT_CHECKED_EXIST)
        }
        return if (cachedPrimaryInfo.password == encodedPassword) {
            setUserId(cachedPrimaryInfo.accountId)
            cachedPrimaryInfo.accountId
        } else {
            null
        }
    }

    override suspend fun signOut() {
        setUserId(null)
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
            _cachedPrimaryInfo = jsonConverter.fromJson(this, AccountPrimaryDataModel::class.java)
        }
    }

    private fun setUserId(accountId: String?) {
        val editor = sharedPreferences.edit()
        editor.putString(USER_ID_KEY, accountId)
        editor.apply()
    }
}