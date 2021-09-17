package com.example.task1.di.dagger.component

import com.example.task1.CountryApp
import com.example.task1.di.dagger.module.ApplicationModule
import com.example.task1.di.dagger.module.NetworkModule
import com.example.task1.di.dagger.viewModels.DaggerViewModelFactory
import com.example.task1.di.dagger.viewModels.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Component(modules = [ApplicationModule::class, NetworkModule::class, ViewModelModule::class])
@Singleton
interface ApplicationComponent {

    @Component.Builder
    interface Builder {
        fun build(): ApplicationComponent

        @BindsInstance
        fun application(app: CountryApp): Builder
    }

    fun provideDaggerViewModelFactory(): DaggerViewModelFactory
}