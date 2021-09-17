package com.example.task1.fragments.news

sealed class NewsIntent {
    object News: NewsIntent()
    data class getNews(val name: Double) : NewsIntent()
}