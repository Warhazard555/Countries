package com.example.task1

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.internal.synchronized

@Database(entities = arrayOf(TableModel::class), version = 1, exportSchema = false)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun CountryDao(): CountryDao

    companion object {
        @Volatile
        private var instance: CountryDatabase? = null

        @InternalCoroutinesApi
        fun getDatabaseInst(context: Context): CountryDatabase {

            if (instance != null) return instance as CountryDatabase

            synchronized(this) {

                if (instance == null)
                    instance = Room
                        .databaseBuilder(context, CountryDatabase::class.java, "Country")
                        .fallbackToDestructiveMigration()
                        .build()
                return instance as CountryDatabase
            }
        }
    }
}