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
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApplicationModule(private val applicationContext: Context? = null) {
    private var sharedPreferences: SharedPreferences? = null
    get() {
        if (field == null) {
            field = applicationContext?.getSharedPreferences(applicationContext.getString(R.string.shared_preference_name), Context.MODE_PRIVATE)
        }
        return field
    }
    private val initializationRepository: IInitializationRepository = InitializationRepository(sharedPreferences)
    private val accountRepository: IAccountRepository = AccountRepository(sharedPreferences)

    @Provides
    @Singleton
    fun provideAccountRepository(): IInitializationRepository = initializationRepository

    @Provides
    @Singleton
    fun provideInitializationRepository(): IAccountRepository = accountRepository
}