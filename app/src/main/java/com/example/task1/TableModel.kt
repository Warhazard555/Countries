package com.example.task1

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "Country")
data class TableModel(

    @ColumnInfo(name = "country")
    var country: String,

    @ColumnInfo(name = "capital")
    var capital: String,

    @ColumnInfo(name = "area")
    var area: Double
)
{
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null
}


