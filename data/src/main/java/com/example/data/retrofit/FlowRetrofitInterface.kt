package com.example.data.retrofit

import com.example.data.CAPITAL_URL
import com.example.domain.dto.CapitalDto
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET


interface FlowRetrofitInterface {

    @GET(CAPITAL_URL)
    fun getAllCapital(): Flow<MutableList<CapitalDto>>
}