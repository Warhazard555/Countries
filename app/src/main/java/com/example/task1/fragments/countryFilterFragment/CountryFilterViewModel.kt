package com.example.task1.fragments.countryFilterFragment

import androidx.lifecycle.SavedStateHandle
import com.example.task1.base.mvvm.BaseViewModel
import com.example.task1.base.mvvm.Outcome
import com.example.task1.data.CountryItem

class CountryFilterViewModel(savedStateHandle: SavedStateHandle) : BaseViewModel(savedStateHandle) {
    var countryFilterLivedata =
        savedStateHandle.getLiveData<Outcome<MutableList<CountryItem>>>("countryFilter")
}