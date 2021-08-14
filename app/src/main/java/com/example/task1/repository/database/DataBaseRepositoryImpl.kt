package com.example.task1.repository.database

import com.example.task1.data.CountryItemDto
import com.example.task1.room.CountryDatabase
import com.example.task1.room.TableModel
import com.example.task1.room.convertToDTO
import io.reactivex.rxjava3.core.Flowable

class DataBaseRepositoryImpl(private val dataBase: CountryDatabase) : DataBaseRepository{

    override fun insertDatabase(list: List<TableModel>) = dataBase.сountryDao().insertDatabase(list)

    override fun getCountryDetals(): Flowable<MutableList<CountryItemDto>> = dataBase.сountryDao().getCountryDetals().map { it.convertToDTO() }

}