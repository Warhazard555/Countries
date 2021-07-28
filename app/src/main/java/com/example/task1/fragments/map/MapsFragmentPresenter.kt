package com.example.task1.fragments.map

import com.example.task1.base.mvp.BaseMvpPresenter
import com.example.task1.retrofit.RetrofitService

class MapsFragmentPresenter : BaseMvpPresenter<MapsFragmentView>() {

    fun getCountryLocation() {
        addDisposable(
            inBackground(
                RetrofitService.getInstance().getData()
            ).subscribe({
                getView()?.showAllCountryMarkers(it)
            }, { it.message?.let { it1 -> getView()?.showError(it1, it) } })
        )
    }
}