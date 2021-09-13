package com.example.data.repository.network

import com.example.data.ext.modifyFlow
import com.example.data.model.CapitalModel
import com.example.data.retrofit.FlowRetrofitInterface
import com.example.domain.dto.CapitalDto
import com.example.domain.outcome.Outcome
import com.example.domain.outcome.Transformer
import com.example.domain.repository.NetworkCapitalsFlowRepository
import kotlinx.coroutines.flow.Flow


class NetworkCapitalFlowRepositoryImpl(
    private val networkCapitalsFlowRepository: FlowRetrofitInterface,
    private val capitalListTransformer: Transformer<List<CapitalModel>, MutableList<CapitalDto>>
) : NetworkCapitalsFlowRepository {

    override fun getAllCapital(): Flow<Outcome<MutableList<CapitalDto>>> =
        modifyFlow(networkCapitalsFlowRepository.getAllCapital(), capitalListTransformer)

    override fun getCapitalByName(name: String): Flow<Outcome<MutableList<CapitalDto>>> =
        modifyFlow(networkCapitalsFlowRepository.getCapitalByName(name), capitalListTransformer)


}