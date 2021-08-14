package com.example.task1.base.mvp

import android.os.Bundle
import org.koin.androidx.scope.ScopeFragment

abstract class BaseMvpFragment<ViewType : BaseMvpView, PresenterType : BaseMvpPresenter<ViewType>> :
    ScopeFragment() {

    protected lateinit var mPresenter: PresenterType

    abstract fun createPresenter()

    abstract fun getPresenter(): PresenterType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createPresenter()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        getPresenter().onDestroyView()
        getPresenter().detachView()
    }
}