package com.example.task1.room


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDatabase(tableModel: TableModel)

    @Query("SELECT * FROM country")
    fun getCountryDetals(): MutableList<TableModel>


}