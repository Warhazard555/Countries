package com.example.task1.fragments.news

import com.example.task1.base.mvi.ViewIntent

sealed class NewsIntent : ViewIntent {
    object News : NewsIntent()

}