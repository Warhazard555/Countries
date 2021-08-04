package com.example.task1.room


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Country")
data class TableModel(

    @PrimaryKey @ColumnInfo
    val name: String,

    @ColumnInfo
    val capital: String,

    @ColumnInfo
    val area: Double,

    @ColumnInfo
    val language: String,

    @ColumnInfo
    val population: Int


)



