package com.example.task1.fragments.countryList

import androidx.lifecycle.SavedStateHandle
import com.example.task1.base.mvvm.BaseViewModel
import com.example.task1.base.mvvm.Outcome
import com.example.task1.base.mvvm.executeJob
import com.example.task1.data.CountryItem
import com.example.task1.retrofit.RetrofitService

class BlankFragmentViewModel(savedStateHandle: SavedStateHandle) : BaseViewModel(savedStateHandle) {
    val mCountryLiveData =
        savedStateHandle.getLiveData<Outcome<MutableList<CountryItem>>>("CountryItem")

    fun getCountryByName() {
        mCompositeDisposable.add(
            executeJob(
                RetrofitService.getInstance().getData(), mCountryLiveData
            )
        )
    }
}