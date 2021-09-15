package com.example.data.room


import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.model.TableModel

@Database(
    entities = [TableModel::class],
    version = 1,
    exportSchema = false
)
abstract class CountryDatabase : RoomDatabase() {

    abstract fun сountryDao(): CountryDao

    companion object {
        @Volatile
        private var instance: CountryDatabase? = null

        fun getDatabaseInst(context: Context): CountryDatabase {

            if (instance != null) return instance as CountryDatabase

            if (instance == null)
                instance = Room
                    .databaseBuilder(context, CountryDatabase::class.java, "TableCountry")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build()
            return instance as CountryDatabase

        }
    }
}