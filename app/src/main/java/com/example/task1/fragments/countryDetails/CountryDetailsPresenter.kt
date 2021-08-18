package com.example.task1.fragments.countryDetails

import com.example.domain.useCase.impl.GetCountryByNameUseCase
import com.example.task1.base.mvp.BaseMvpPresenter

class CountryDetailsPresenter(private val getCountryByNameUseCase: GetCountryByNameUseCase) :
    BaseMvpPresenter<CountryDetailsView>() {
    lateinit var mCountryName: String

    fun setCountryName(country: String) {
        mCountryName = country
    }

    fun getCountryByName() {
        addDisposable(
            inBackground(
                getCountryByNameUseCase.setParams(mCountryName).execute()
            ).subscribe({
                getView()?.showCountryInfo(it[0])
            }, { it.message?.let { it1 -> getView()?.showError(it1, it) } })
        )
    }
}