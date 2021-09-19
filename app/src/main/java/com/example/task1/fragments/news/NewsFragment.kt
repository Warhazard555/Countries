package com.example.task1.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.task1.R
import com.example.task1.base.mvi.BaseFragmentMVI
import com.example.task1.ext.showAlertDialog
import kotlinx.android.synthetic.main.fragment_news_list.*

class NewsFragment :
    BaseFragmentMVI<NewsIntent, NewsAction, NewsState, NewsViewModel>(NewsViewModel::class.java) {
    private var newsAdapter = NewsAdapter()
    private lateinit var srNews: SwipeRefreshLayout
    private lateinit var progressBar: FrameLayout

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        srNews = view.findViewById(R.id.sr_news)
        progressBar = view.findViewById(R.id.news_progressBar)

    }

    override fun initData() {
        dispatchIntent(NewsIntent.News)
    }

    override fun initUI() {
        recycler_news.adapter = newsAdapter
    }

    override fun render(state: NewsState) {
        when (state) {
            is NewsState.Exception -> {
                activity?.showAlertDialog()
                srNews.isRefreshing = false
            }
            is NewsState.Loading -> {
                progressBar.visibility = if (state.loading) View.VISIBLE else View.GONE

            }
            is NewsState.ResultNews -> {
                newsAdapter.submitList(state.data)
                srNews.isRefreshing = false
                if (srNews.isRefreshing){progressBar.visibility =View.GONE}
                srNews.setOnRefreshListener {
                    render(state)
                    initUI()
                    initData()
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_news_list, container, false)
    }


}