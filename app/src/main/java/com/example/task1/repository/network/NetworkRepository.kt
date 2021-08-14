package com.example.task1.repository.network

import com.example.task1.data.CountryItemDto
import io.reactivex.rxjava3.core.Flowable

interface NetworkRepository {

    fun getData(): Flowable<MutableList<CountryItemDto>>

    fun getCountryByName(name: String): Flowable<MutableList<CountryItemDto>>
}