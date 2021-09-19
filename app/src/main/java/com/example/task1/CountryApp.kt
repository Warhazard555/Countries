package com.example.task1

import android.app.Application
import com.example.task1.di.*
import com.example.task1.di.dagger.component.ApplicationComponent
import com.example.task1.di.dagger.component.DaggerApplicationComponent
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class CountryApp : Application() {

    companion object {
        lateinit var appComponents: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()
        appComponents = initDI()
        startKoin {
            // Koin Android logger
            androidLogger()
            //inject Android context
            androidContext(this@CountryApp)
            // use modules
            modules(
                appModule,
                countryListModule,
                countryDetailsModule,
                countryFilterModule,
                mapModule,
                capitalModule
            )
        }
    }
    private fun initDI() : ApplicationComponent =
        DaggerApplicationComponent.builder().application(this).build()
}