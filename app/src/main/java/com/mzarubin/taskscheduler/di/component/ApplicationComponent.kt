package com.mzarubin.taskscheduler.di.component

import com.mzarubin.taskscheduler.TaskSchedulerApplication
import com.mzarubin.taskscheduler.di.module.ApplicationModule
import com.mzarubin.taskscheduler.di.module.FragmentBindingModule
import com.mzarubin.taskscheduler.di.module.RepositoryModule
import com.mzarubin.taskscheduler.di.viewmodel.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, AndroidSupportInjectionModule::class,
        ApplicationModule::class, RepositoryModule::class,
        ViewModelModule::class, FragmentBindingModule::class]
)
interface ApplicationComponent : AndroidInjector<TaskSchedulerApplication> {
    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(applicationContext: TaskSchedulerApplication): Builder
        fun build(): ApplicationComponent
    }
}