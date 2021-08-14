package com.example.task1.room


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.rxjava3.core.Flowable

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDatabase(list: List<TableModel>)

    @Query("SELECT * FROM country")
    fun getCountryDetals(): Flowable<MutableList<TableModel>>
}