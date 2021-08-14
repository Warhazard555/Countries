package com.example.task1

import android.app.Application
import com.example.task1.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin


class CountryApp : Application() {

    override fun onCreate() {
        super.onCreate()

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
                mapModule
            )
        }

    }
}