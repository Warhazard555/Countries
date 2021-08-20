package com.example.data.retrofit

import com.example.data.CAPITAL_URL
import com.example.domain.dto.CapitalDto
import kotlinx.coroutines.Deferred
import retrofit2.http.GET

interface CoroutinesRetrofitInterface {

    @GET(CAPITAL_URL)
    suspend fun getAllCapital(): MutableList<CapitalDto>
}