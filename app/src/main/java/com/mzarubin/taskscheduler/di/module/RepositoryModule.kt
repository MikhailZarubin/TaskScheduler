package com.mzarubin.taskscheduler.di.module

import android.content.Context
import android.content.SharedPreferences
import com.mzarubin.taskscheduler.R
import com.mzarubin.taskscheduler.core.repository.IAccountRepository
import com.mzarubin.taskscheduler.core.repository.IInitializationRepository
import com.mzarubin.taskscheduler.core.repository.IUserDataRepository
import com.mzarubin.taskscheduler.core.repository.impl.AccountRepository
import com.mzarubin.taskscheduler.core.repository.impl.InitializationRepository
import com.mzarubin.taskscheduler.core.repository.impl.UserDataRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
    @Provides
    @Singleton
    fun provideInitializationRepository(sharedPreferences: SharedPreferences): IInitializationRepository =
        InitializationRepository(sharedPreferences)

    @Provides
    @Singleton
    fun provideAccountRepository(sharedPreferences: SharedPreferences): IAccountRepository =
        AccountRepository(sharedPreferences)

    @Provides
    @Singleton
    fun provideUserDataRepository(sharedPreferences: SharedPreferences): IUserDataRepository =
        UserDataRepository(sharedPreferences)

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context): SharedPreferences =
        context.getSharedPreferences(
            context.getString(R.string.shared_preference_name),
            Context.MODE_PRIVATE
        )
}