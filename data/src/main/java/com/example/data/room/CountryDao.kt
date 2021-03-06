package com.example.data.room


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.model.TableModel
import io.reactivex.rxjava3.core.Flowable

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDatabase(list: MutableList<TableModel>)

    @Query("SELECT * FROM country")
    fun getAllCountryDB(): Flowable<MutableList<TableModel>>

    @Query("SELECT * FROM country WHERE name IN (:name)")
    fun getCountryByNameDB(name: String): Flowable<TableModel>
}