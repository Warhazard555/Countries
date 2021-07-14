package com.example.task1.retrofit

import com.example.task1.GET_COUNTRY_BY_NAME
import com.example.task1.PATH_VARIABLE
import com.example.task1.data.CountryItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RetrofitInterface {

    @GET(value = "all")
    fun getData(): Call<MutableList<CountryItem>>

    @GET(GET_COUNTRY_BY_NAME)
    fun getCountryByName(@Path(PATH_VARIABLE) name: String): Call<MutableList<CountryItem>>
}