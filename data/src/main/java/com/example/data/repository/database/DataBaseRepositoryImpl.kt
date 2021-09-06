package com.example.data.repository.database

import com.example.data.model.convertToDTO
import com.example.data.model.convertToTableModel
import com.example.data.room.CountryDatabase
import com.example.domain.dto.CountryItemDto
import com.example.domain.repository.DataBaseRepository
import io.reactivex.rxjava3.core.Flowable

class DataBaseRepositoryImpl(private val dataBase: CountryDatabase) : DataBaseRepository {

    override fun insertDatabase(list: MutableList<CountryItemDto>) =
        dataBase.сountryDao().insertDatabase(list.convertToTableModel())

    override fun getAllCountryDB(): Flowable<MutableList<CountryItemDto>> =
        dataBase.сountryDao().getAllCountryDB().map { it.convertToDTO() }

}