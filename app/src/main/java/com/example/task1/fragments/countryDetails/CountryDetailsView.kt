package com.example.task1.fragments.countryDetails

import com.example.domain.dto.CountryItemDto
import com.example.task1.base.mvp.BaseMvpView

interface CountryDetailsView : BaseMvpView {

    fun showCountryInfo(country: CountryItemDto)
}