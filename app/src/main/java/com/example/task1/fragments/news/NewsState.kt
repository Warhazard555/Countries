package com.example.task1.fragments.news

import com.example.domain.dto.NewsDto
import com.example.task1.base.mvi.ViewState

sealed class NewsState: ViewState {
    data class Loading(val loading: Boolean) : NewsState()
    data class Exception(val error: Throwable) : NewsState()
    data class ResultNews(val data: List<NewsDto>) : NewsState()
}