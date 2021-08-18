package com.example.task1.fragments.map

import com.example.domain.useCase.impl.GetAllCountryUseCase
import com.example.task1.base.mvp.BaseMvpPresenter

class MapsFragmentPresenter(private val getAllCountryUseCase: GetAllCountryUseCase) :
    BaseMvpPresenter<MapsFragmentView>() {

    fun getCountryLocation() {
        addDisposable(
            inBackground(
                getAllCountryUseCase.execute()
            ).subscribe({
                getView()?.showAllCountryMarkers(it)
            }, { it.message?.let { it1 -> getView()?.showError(it1, it) } })
        )
    }
}