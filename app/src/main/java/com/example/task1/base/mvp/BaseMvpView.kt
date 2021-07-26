package com.example.task1.base.mvp

interface BaseMvpView {

    fun showError(error: String, throwable: Throwable)

    fun showProgress()

    fun hideProgress()
}