package com.example.data.retrofit

import com.example.data.CAPITAL_URL
import com.example.data.GET_COUNTRY_BY_NAME
import com.example.data.PATH_VARIABLE
import com.example.data.model.CapitalModel
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Path


interface FlowRetrofitInterface {

    @GET(CAPITAL_URL)
    fun getAllCapital(): Flow<List<CapitalModel>>

    @GET(GET_COUNTRY_BY_NAME)
    fun getCapitalByName(@Path(PATH_VARIABLE) name: String): Flow<List<CapitalModel>>
}