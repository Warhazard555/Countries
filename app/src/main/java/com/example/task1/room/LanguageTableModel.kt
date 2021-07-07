package com.example.task1.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Languages")
data class LanguageTableModel(
    @PrimaryKey @ColumnInfo
    val name: String,
    @ColumnInfo
    val language: String
    )
