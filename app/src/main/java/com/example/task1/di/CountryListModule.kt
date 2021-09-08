package com.example.task1.di

import androidx.lifecycle.SavedStateHandle
import com.example.domain.useCase.impl.GetAllCountryDbUseCase
import com.example.domain.useCase.impl.GetAllCountryUseCase
import com.example.domain.useCase.impl.GetCountryByNameDbUseCase
import com.example.domain.useCase.impl.GetCountryByNameUseCase
import com.example.task1.fragments.countryList.CountryListFragment
import com.example.task1.fragments.countryList.CountryListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val countryListModule = module {
    scope<CountryListFragment> {

        scoped { GetAllCountryDbUseCase(get()) }
        scoped { GetCountryByNameUseCase(get()) }
        scoped { GetAllCountryUseCase(get()) }
        scoped { GetCountryByNameDbUseCase(get()) }
        viewModel { (handle: SavedStateHandle) ->
            CountryListViewModel(
                handle,
                get(),
                get(),
                get(),
                get()
            )
        }
    }
}