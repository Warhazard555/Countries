package com.example.domain.repository

import com.example.domain.dto.CapitalDto


interface NetworkCapitalRepository {


    suspend fun getAllCapital(): MutableList<CapitalDto>
}