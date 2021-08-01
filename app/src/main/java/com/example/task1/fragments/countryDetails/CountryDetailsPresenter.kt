package com.example.task1.fragments.countryDetails

import com.example.task1.base.mvp.BaseMvpPresenter
import com.example.task1.retrofit.RetrofitService

class CountryDetailsPresenter : BaseMvpPresenter<CountryDetailsView>(){
    lateinit var mCountryName: String

    fun setCountryName(country: String) {
        mCountryName = country
    }

    fun getCountryByName(isRefresh: Boolean) {
        addDisposable(
            inBackground(
                RetrofitService.getInstance().getCountryByName(mCountryName)
            ).subscribe({
                getView()?.showCountryInfo(it[0])
            }, { it.message?.let { it1 -> getView()?.showError(it1, it) } })
        )
    }
}