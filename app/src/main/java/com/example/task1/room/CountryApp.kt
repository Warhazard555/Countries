package com.example.task1.room

import android.app.Application



class CountryApp: Application() {

    companion object{

        lateinit var mCountryDatabase: CountryDatabase
        var countryDao: CountryDao? = null

    }

    override fun onCreate() {
        super.onCreate()
        mCountryDatabase = CountryDatabase.getDatabaseInst(this)
        countryDao = mCountryDatabase.сountryDao()

    }
}