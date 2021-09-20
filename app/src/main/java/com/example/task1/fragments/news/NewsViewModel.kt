package com.example.task1.fragments.news

import android.content.Context
import android.location.Geocoder
import com.example.data.RU
import com.example.domain.repository.NetworkNewsRepository
import com.example.task1.base.mvi.BaseViewModelMVI
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class NewsViewModel @Inject constructor(
    context: Context,
    private val dataManager: NetworkNewsRepository
) : BaseViewModelMVI<NewsIntent, NewsAction, NewsState>() {

    override fun handleAction(action: NewsAction) {
        launchOnUI {
            when (action) {
                is NewsAction.News -> {
                    dataManager.getNews(RU).collect {
                        mState.value = (it.reduce())
                    }
                }
            }
        }
    }

    override fun intentToAction(intent: NewsIntent): NewsAction {
        return when (intent) {
            is NewsIntent.News -> NewsAction.News

        }
    }
}