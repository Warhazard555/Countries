package com.example.task1.di

import com.example.domain.useCase.impl.GetCountryByNameUseCase
import com.example.task1.fragments.countryDetails.CountryDetailsFragment
import com.example.task1.fragments.countryDetails.CountryDetailsPresenter
import org.koin.dsl.module

val countryDetailsModule = module {

    scope<CountryDetailsFragment> {
        scoped { CountryDetailsPresenter(get()) }
        scoped { GetCountryByNameUseCase(get()) }
    }


}