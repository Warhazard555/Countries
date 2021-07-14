package com.example.task1.room


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.task1.GET_COUNTRY_BY_NAME
import com.example.task1.PATH_VARIABLE
import com.example.task1.data.CountryItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDatabase(List: List<TableModel>)

    @Query("SELECT * FROM country")
    fun getCountryDetals(): MutableList<TableModel>



}