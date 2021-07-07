package com.example.task1.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
@Dao
interface LanguageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertDatabase(LanguageTableModel: LanguageTableModel)

    @Query("SELECT language FROM languages WHERE name = :name")
    fun getLanguageDetals(name: String): MutableList<String>
}