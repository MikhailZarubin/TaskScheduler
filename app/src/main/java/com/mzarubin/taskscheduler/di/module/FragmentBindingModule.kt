package com.mzarubin.taskscheduler.di.module

import com.mzarubin.taskscheduler.di.scope.FragmentScoped
import com.mzarubin.taskscheduler.ui.initialization.InitializationFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun provideInitializationFragment(): InitializationFragment
}