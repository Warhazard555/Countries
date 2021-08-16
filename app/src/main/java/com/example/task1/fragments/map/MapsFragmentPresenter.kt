package com.example.task1.fragments.map

import com.example.domain.repository.NetworkRepository
import com.example.task1.base.mvp.BaseMvpPresenter

class MapsFragmentPresenter(private val mNetworkRepository: NetworkRepository) :
    BaseMvpPresenter<MapsFragmentView>() {

    fun getCountryLocation() {
        addDisposable(
            inBackground(
                mNetworkRepository.getData()
            ).subscribe({
                getView()?.showAllCountryMarkers(it)
            }, { it.message?.let { it1 -> getView()?.showError(it1, it) } })
        )
    }
}