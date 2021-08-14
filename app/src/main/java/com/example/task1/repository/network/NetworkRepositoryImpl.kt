package com.example.task1.repository.network

import com.example.task1.data.CountryItemDto
import com.example.task1.retrofit.RetrofitInterface
import io.reactivex.rxjava3.core.Flowable

class NetworkRepositoryImpl(private val retrofitService: RetrofitInterface): NetworkRepository {

    override fun getData(): Flowable<MutableList<CountryItemDto>> = retrofitService.getData()

    override fun getCountryByName(name: String): Flowable<MutableList<CountryItemDto>> = retrofitService.getCountryByName(name)


}