package com.example.task1.fragments.countryDetails

import com.example.task1.base.mvp.BaseMvpPresenter
import com.example.task1.retrofit.RetrofitService

class CountryDetailsPresenter : BaseMvpPresenter<CountryDetailsView>(){

    fun getCountryByName(name: String, isRefresh: Boolean) {
        addDisposable(
            inBackground(
                RetrofitService.getInstance().getCountryByName(name)
            ).subscribe({
                getView()?.showCountryInfo(it[0])
            }, { it.message?.let { it1 -> getView()?.showError(it1, it) } })
        )
    }
}