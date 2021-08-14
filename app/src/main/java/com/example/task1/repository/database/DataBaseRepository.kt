package com.example.task1.repository.database

import com.example.task1.data.CountryItemDto
import com.example.task1.room.TableModel
import io.reactivex.rxjava3.core.Flowable

interface DataBaseRepository {

    fun insertDatabase(list: List<TableModel>)

    fun getCountryDetals(): Flowable<MutableList<CountryItemDto>>
}