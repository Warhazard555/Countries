package com.example.task1.fragments.countryFilterFragment

import androidx.lifecycle.SavedStateHandle
import com.example.domain.dto.CountryItemDto
import com.example.domain.outcome.Outcome
import com.example.domain.useCase.impl.GetAllCountryUseCase
import com.example.task1.base.mvvm.BaseViewModel
import com.example.task1.base.mvvm.executeJob


class CountryFilterViewModel(
    savedStateHandle: SavedStateHandle,
    private val getAllCountryUseCase: GetAllCountryUseCase
) : BaseViewModel(savedStateHandle) {
    var mCountryFilterLivedata =
        savedStateHandle.getLiveData<Outcome<MutableList<CountryItemDto>>>("countryFilter")


    fun getCountryList() {
        mCompositeDisposable.add(
            executeJob(
                getAllCountryUseCase.execute(),
                mCountryFilterLivedata
            )
        )
    }

}

