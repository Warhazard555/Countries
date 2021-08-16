package com.example.task1.fragments.map

import com.example.domain.dto.CountryItemDto
import com.example.task1.base.mvp.BaseMvpView

interface MapsFragmentView : BaseMvpView {
    fun showAllCountryMarkers(country: MutableList<CountryItemDto>)
}