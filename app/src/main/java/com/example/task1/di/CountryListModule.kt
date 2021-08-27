package com.example.task1.di

import androidx.lifecycle.SavedStateHandle
import com.example.domain.useCase.impl.GetAllCountryDbUseCase
import com.example.domain.useCase.impl.GetAllCountryUseCase
import com.example.domain.useCase.impl.GetCountryByNameUseCase
import com.example.task1.fragments.countryList.BlankFragment
import com.example.task1.fragments.countryList.BlankFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val countryListModule = module {
    scope<BlankFragment> {

        scoped { GetAllCountryDbUseCase(get()) }
        scoped { GetCountryByNameUseCase(get()) }
        scoped { GetAllCountryUseCase(get()) }
        viewModel { (handle: SavedStateHandle) ->
            BlankFragmentViewModel(
                handle,
                get(),
                get(),
                get(),
                get()
            )
        }
    }
}