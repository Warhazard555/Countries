package com.example.task1.fragments.news

import com.example.domain.dto.NewsDto
import com.example.task1.base.mvi.ViewState

sealed class NewsState: ViewState {
    object Loading: NewsState()
    data class Exception(val error: Throwable): NewsState()
    data class ResultNews(val data: MutableList<NewsDto>): NewsState()
}