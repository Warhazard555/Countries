package com.example.data.retrofit

import com.example.data.API_ALL
import com.example.data.GET_COUNTRY_BY_NAME
import com.example.data.PATH_VARIABLE
import com.example.domain.dto.CountryItemDto
import io.reactivex.rxjava3.core.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitInterface {

    @GET(API_ALL)
    fun getData(): Flowable<MutableList<CountryItemDto>>

    @GET(GET_COUNTRY_BY_NAME)
    fun getCountryByName(@Path(PATH_VARIABLE) name: String): Flowable<MutableList<CountryItemDto>>
}