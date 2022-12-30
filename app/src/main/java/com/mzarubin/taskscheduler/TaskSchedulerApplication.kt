package com.mzarubin.taskscheduler

import android.app.Application
import com.mzarubin.taskscheduler.di.component.DaggerApplicationComponent
import com.mzarubin.taskscheduler.di.module.ApplicationModule
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class TaskSchedulerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(applicationContext)).build()
    }
}