package com.example.task1.fragments.countryFilterFragment

import androidx.lifecycle.SavedStateHandle
import com.example.task1.base.mvvm.BaseViewModel
import com.example.task1.base.mvvm.Outcome
import com.example.task1.base.mvvm.executeJob
import com.example.task1.data.CountryItemDto
import com.example.task1.repository.network.NetworkRepository


class CountryFilterViewModel(
    savedStateHandle: SavedStateHandle,
    private val mNetworkRepository: NetworkRepository
) : BaseViewModel(savedStateHandle) {
    var mCountryFilterLivedata =
        savedStateHandle.getLiveData<Outcome<MutableList<CountryItemDto>>>("countryFilter")


    fun getCountryByName() {
        mCompositeDisposable.add(
            executeJob(
                mNetworkRepository.getData(),
                mCountryFilterLivedata
            )
        )
    }

}

