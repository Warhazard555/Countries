package com.example.task1.fragments.countryFilterFragment

import androidx.lifecycle.SavedStateHandle
import com.example.data.model.TableModel
import com.example.domain.dto.CountryItemDto
import com.example.domain.outcome.Outcome
import com.example.domain.useCase.impl.GetAllCountryDbUseCase
import com.example.domain.useCase.impl.GetAllCountryUseCase
import com.example.task1.base.mvvm.BaseViewModel
import com.example.task1.base.mvvm.executeJob


class CountryFilterViewModel(
    savedStateHandle: SavedStateHandle,
    private val getAllCountryDbUseCase: GetAllCountryDbUseCase
) : BaseViewModel(savedStateHandle) {
    var mCountryFilterLivedata =
        savedStateHandle.getLiveData<Outcome<MutableList<CountryItemDto>>>("countryFilter")
    

    fun getCountryListDB(){
        mCompositeDisposable.add(
            executeJob(
                getAllCountryDbUseCase.execute(),
                mCountryFilterLivedata
            )
        )
    }

}

