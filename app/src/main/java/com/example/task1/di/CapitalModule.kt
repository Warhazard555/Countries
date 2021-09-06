package com.example.task1.di

import androidx.lifecycle.SavedStateHandle
import com.example.domain.useCase.impl.GetAllCapitalUseCase
import com.example.task1.fragments.capitalFragment.CapitalFragment
import com.example.task1.fragments.capitalFragment.CapitalFragmentViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val capitalModule = module {
    scope<CapitalFragment> {
        scoped { GetAllCapitalUseCase(get()) }
        viewModel { (handle: SavedStateHandle) -> CapitalFragmentViewModel(handle, get(), get()) }
    }
}