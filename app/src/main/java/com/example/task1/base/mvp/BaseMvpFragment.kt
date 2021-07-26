package com.example.task1.base.mvp

import android.os.Bundle
import androidx.fragment.app.Fragment

abstract class BaseMvpFragment<ViewType : BaseMvpView, PresenterType : BaseMvpPresenter<ViewType>> : Fragment() {

    protected lateinit var mPresenter : PresenterType

    abstract fun createPresenter()

    abstract fun getPresenter(): PresenterType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createPresenter()
    }
}