package com.example.task1.room

import android.app.Application

class CountryApp: Application() {

    companion object{

        lateinit var dCountryDatabase: CountryDatabase
    }

    override fun onCreate() {
        super.onCreate()

        dCountryDatabase = CountryDatabase.getDatabaseInst(this)
    }
}