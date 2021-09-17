package com.example.data.retrofit

import com.example.data.GET_NEWS
import com.example.data.NEWS_API_KEY
import com.example.data.model.news.ArticlesList
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface NewsRetrofitInterface {

    @Headers(NEWS_API_KEY)
    @GET(GET_NEWS)
    fun getNews(@Query("country") name:String): Flow<ArticlesList>
}