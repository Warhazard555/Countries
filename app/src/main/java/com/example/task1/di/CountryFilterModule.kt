package com.example.task1.di

import androidx.lifecycle.SavedStateHandle
import com.example.task1.fragments.countryFilterFragment.CountryFilterFragment
import com.example.task1.fragments.countryFilterFragment.CountryFilterViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val countryFilterModule = module {
    scope<CountryFilterFragment> {
        viewModel { (handle: SavedStateHandle) ->
            CountryFilterViewModel(
                handle,
                get()
            )
        }
    }
}