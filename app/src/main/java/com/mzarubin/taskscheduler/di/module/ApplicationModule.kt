package com.mzarubin.taskscheduler.di.module

import android.content.Context
import com.mzarubin.taskscheduler.TaskSchedulerApplication
import dagger.Binds
import dagger.Module

@Module
abstract class ApplicationModule {
    @Binds
    abstract fun bindContext(application: TaskSchedulerApplication): Context
}