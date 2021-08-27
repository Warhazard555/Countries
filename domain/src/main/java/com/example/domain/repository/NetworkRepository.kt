package com.example.domain.repository

import com.example.domain.dto.CountryItemDto
import io.reactivex.rxjava3.core.Flowable


interface NetworkRepository {

    fun getData(): Flowable<MutableList<CountryItemDto>>

    fun getCountryByName(name: String): Flowable<MutableList<CountryItemDto>>
}