package com.example.data.model.news

import com.example.data.model.news.Article

data class NewsData(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)

