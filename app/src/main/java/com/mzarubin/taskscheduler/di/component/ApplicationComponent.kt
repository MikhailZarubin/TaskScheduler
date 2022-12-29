package com.mzarubin.taskscheduler.di.component

import com.mzarubin.taskscheduler.core.repository.IAccountRepository
import com.mzarubin.taskscheduler.core.repository.IInitializationRepository
import com.mzarubin.taskscheduler.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun getAccountRepository(): IAccountRepository
    fun getInitializationRepository(): IInitializationRepository
}