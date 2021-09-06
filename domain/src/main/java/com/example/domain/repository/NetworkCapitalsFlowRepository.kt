package com.example.domain.repository

import com.example.domain.dto.CapitalDto
import com.example.domain.outcome.Outcome
import kotlinx.coroutines.flow.Flow


interface NetworkCapitalsFlowRepository {

    fun getAllCapital(): Flow<Outcome<List<CapitalDto>>>
}