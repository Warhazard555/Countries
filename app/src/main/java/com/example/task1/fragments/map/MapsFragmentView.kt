package com.example.task1.fragments.map

import com.example.task1.base.mvp.BaseMvpView
import com.example.task1.data.CountryItem

interface MapsFragmentView : BaseMvpView {
    fun showAllCountryMarkers(country: MutableList<CountryItem>)
}