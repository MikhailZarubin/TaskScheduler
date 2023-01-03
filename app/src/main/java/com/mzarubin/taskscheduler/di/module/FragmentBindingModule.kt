package com.mzarubin.taskscheduler.di.module

import com.mzarubin.taskscheduler.di.scope.FragmentScoped
import com.mzarubin.taskscheduler.ui.initialization.fragment.AuthorizationFragment
import com.mzarubin.taskscheduler.ui.initialization.fragment.InitializationFragment
import com.mzarubin.taskscheduler.ui.initialization.fragment.SignUpFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBindingModule {
    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun provideInitializationFragment(): InitializationFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun provideAuthorizationFragment(): AuthorizationFragment

    @FragmentScoped
    @ContributesAndroidInjector
    abstract fun provideSignUpFragment(): SignUpFragment
}