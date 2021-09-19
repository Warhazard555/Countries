package com.example.task1.fragments.news

import com.example.task1.base.mvi.ViewAction

sealed class NewsAction: ViewAction  {
    object News: NewsAction()

}