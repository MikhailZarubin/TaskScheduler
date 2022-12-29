package com.mzarubin.taskscheduler.di.module

import android.content.Context
import android.content.SharedPreferences
import com.mzarubin.taskscheduler.R
import com.mzarubin.taskscheduler.core.repository.IAccountRepository
import com.mzarubin.taskscheduler.core.repository.IInitializationRepository
import com.mzarubin.taskscheduler.core.repository.impl.AccountRepository
import com.mzarubin.taskscheduler.core.repository.impl.InitializationRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val applicationContext: Context) {
    private var sharedPreferences: SharedPreferences? = null
    get() {
        if (field == null) {
            field = applicationContext.getSharedPreferences(applicationContext.getString(R.string.shared_preference_name), Context.MODE_PRIVATE)
        }
        return field
    }

    @Provides
    @Singleton
    fun provideInitializationRepository(): IAccountRepository = AccountRepository(sharedPreferences)

    @Provides
    @Singleton
    fun provideAccountRepository(): IInitializationRepository = InitializationRepository(sharedPreferences)
}