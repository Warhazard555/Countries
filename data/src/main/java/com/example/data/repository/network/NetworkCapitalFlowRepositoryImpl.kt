package com.example.data.repository.network

import com.example.data.model.CapitalModel
import com.example.data.retrofit.FlowRetrofitInterface
import com.example.domain.dto.CapitalDto
import com.example.domain.repository.NetworkCapitalsFlowRepository
import com.example.outcome.Outcome
import com.example.outcome.Transformer
import com.example.task1.ext.modifyFlow
import kotlinx.coroutines.flow.Flow


//class NetworkCapitalFlowRepositoryImpl(
//    private val networkCapitalsFlowRepository: FlowRetrofitInterface,
//    private val capitalListTransformer: Transformer<List<CapitalModel>, List<CapitalDto>>
//) : NetworkCapitalsFlowRepository {
//
//    override fun getAllCapital(): Flow<Outcome<List<CapitalDto>>> =
//        modifyFlow(networkCapitalsFlowRepository.getAllCapital(), capitalListTransformer)
//
//}