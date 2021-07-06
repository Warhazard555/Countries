package com.example.task1

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import androidx.lifecycle.LiveData


@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDatabase(tableModel: TableModel)

    @Query("SELECT * FROM country")
    fun getCountryDetals(country: String) : LiveData<TableModel>
}