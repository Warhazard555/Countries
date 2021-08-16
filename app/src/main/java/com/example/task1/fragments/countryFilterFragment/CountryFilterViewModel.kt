package com.example.task1.fragments.countryFilterFragment

import androidx.lifecycle.SavedStateHandle
import com.example.domain.dto.CountryItemDto
import com.example.domain.repository.NetworkRepository
import com.example.task1.base.mvvm.BaseViewModel
import com.example.task1.base.mvvm.Outcome
import com.example.task1.base.mvvm.executeJob


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

