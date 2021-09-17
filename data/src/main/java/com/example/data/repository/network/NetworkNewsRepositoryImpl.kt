package com.example.data.repository.network

import com.example.data.ext.modifyFlow
import com.example.data.model.news.Article
import com.example.data.retrofit.NewsRetrofitInterface
import com.example.domain.dto.NewsDto
import com.example.domain.outcome.Outcome
import com.example.domain.outcome.Transformer
import com.example.domain.repository.NetworkNewsRepository
import io.reactivex.rxjava3.core.Flowable
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NetworkNewsRepositoryImpl(
    private val retrofitService: NewsRetrofitInterface,
    private val transformer: Transformer<List<Article>,List<NewsDto>>
) : NetworkNewsRepository {


    override fun getNews(name: String): Flow<Outcome<List<NewsDto>>> =
        modifyFlow(retrofitService.getNews(name).map { it.articles }, transformer)


}