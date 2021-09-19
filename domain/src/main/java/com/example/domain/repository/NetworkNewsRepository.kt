package com.example.domain.repository

import com.example.domain.dto.NewsDto
import com.example.domain.outcome.Outcome
import kotlinx.coroutines.flow.Flow

interface NetworkNewsRepository {

    fun getNews(name:String): Flow<Outcome<List<NewsDto>>>

}