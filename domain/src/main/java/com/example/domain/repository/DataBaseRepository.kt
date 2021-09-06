package com.example.domain.repository

import com.example.domain.dto.CountryItemDto
import io.reactivex.rxjava3.core.Flowable


interface DataBaseRepository {

    fun insertDatabase(list: MutableList<CountryItemDto>)

    fun getAllCountryDB(): Flowable<MutableList<CountryItemDto>>
}