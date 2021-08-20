package com.example.data.repository.network

import com.example.data.retrofit.CoroutinesRetrofitInterface
import com.example.domain.dto.CapitalDto
import com.example.domain.repository.NetworkCapitalRepository

class NetworkCapitalRepositoryImpl(private val coroutinesNetworkRepository: CoroutinesRetrofitInterface) : NetworkCapitalRepository{

    override suspend fun getAllCapital(): MutableList<CapitalDto> = coroutinesNetworkRepository.getAllCapital()

}