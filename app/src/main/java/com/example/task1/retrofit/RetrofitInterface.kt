package com.example.task1.retrofit

import com.example.task1.data.CountryItem
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitInterface {

    @GET(value = "all")
    fun getData(): Call<MutableList<CountryItem>>
}