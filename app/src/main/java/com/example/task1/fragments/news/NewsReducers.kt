package com.example.task1.fragments.news

import com.example.domain.dto.NewsDto
import com.example.domain.outcome.Outcome

fun Outcome<List<NewsDto>>.reduce(): NewsState {
    return when (this) {
        is Outcome.Failure -> NewsState.Exception(e)
        is Outcome.Next -> NewsState.ResultNews(data)
        is Outcome.Progress -> NewsState.Loading(loading)
        is Outcome.Success -> NewsState.ResultNews(data)
    }
}