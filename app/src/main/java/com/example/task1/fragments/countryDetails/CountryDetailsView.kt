package com.example.task1.fragments.countryDetails

import com.example.task1.base.mvp.BaseMvpView
import com.example.task1.data.CountryItemDto

interface CountryDetailsView : BaseMvpView {

    fun showCountryInfo(country: CountryItemDto)
}