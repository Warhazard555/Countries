package com.example.data.repository.network

import com.example.data.retrofit.RetrofitInterface
import com.example.domain.dto.CountryItemDto
import com.example.domain.repository.NetworkRepository
import io.reactivex.rxjava3.core.Flowable

class NetworkRepositoryImpl(private val retrofitService: RetrofitInterface): NetworkRepository {

    override fun getData(): Flowable<MutableList<CountryItemDto>> = retrofitService.getData()

    override fun getCountryByName(name: String): Flowable<MutableList<CountryItemDto>> = retrofitService.getCountryByName(name)


}