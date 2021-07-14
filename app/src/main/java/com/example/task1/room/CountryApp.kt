package com.example.task1.room

import android.app.Application


class CountryApp: Application() {

    companion object{

        lateinit var mCountryDatabase: CountryDatabase
    }

    override fun onCreate() {
        super.onCreate()

        mCountryDatabase = CountryDatabase.getDatabaseInst(this)
    }
}