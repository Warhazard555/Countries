package com.example.task1.fragments.countryFilterFragment

import androidx.lifecycle.SavedStateHandle
import com.example.task1.base.mvvm.BaseViewModel
import com.example.task1.base.mvvm.Outcome
import com.example.task1.base.mvvm.executeJob
import com.example.task1.data.CountryItem
import com.example.task1.retrofit.RetrofitService


class CountryFilterViewModel(savedStateHandle: SavedStateHandle) : BaseViewModel(savedStateHandle) {
    var mCountryFilterLivedata =
        savedStateHandle.getLiveData<Outcome<MutableList<CountryItem>>>("countryFilter")

    fun getCountryByName() {
        mCompositeDisposable.add(
            executeJob(
                RetrofitService.getInstance().getData(),
                mCountryFilterLivedata
            )
        )
    }


}