package com.example.task1

import android.content.Context
import androidx.lifecycle.LiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch

class Repository {

    companion object {
        var countryDatabase: CountryDatabase? = null

        var tableModel: LiveData<TableModel>? = null


        @InternalCoroutinesApi
        fun initializeDB(context: Context): CountryDatabase {
            return CountryDatabase.getDatabaseInst(context)
        }


        @InternalCoroutinesApi
        fun insertDatabase(context: Context, country: String, capital: String, area: Double) {
            if (countryDatabase == null)
                countryDatabase = initializeDB(context)
            CoroutineScope(IO).launch {
                val countryDetals = TableModel(country, capital, area)
                countryDatabase!!.CountryDao().insertDatabase(countryDetals)
            }

        }


        @InternalCoroutinesApi
        fun getCountryDetals(context: Context, country: String): CountryDatabase? {
            if (countryDatabase == null)
                countryDatabase = initializeDB(context)

            tableModel = countryDatabase!!.CountryDao().getCountryDetals(country)
            return countryDatabase

        }


    }

}