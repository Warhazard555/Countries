package com.example.task1.base.mvp

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers

abstract class BaseMvpPresenter<ViewType : BaseMvpView> {

    private var mView: ViewType? = null
    private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()

    fun attachView(view: ViewType) {
        mView = view
    }

    protected open fun getView(): ViewType? = mView

    fun onDestroyView() {
        mCompositeDisposable.clear()
    }

    fun detachView() {
        mView = null
    }

    fun addDisposable(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    fun <Data> inBackground(flowable: Flowable<Data>): Flowable<Data> {
        return flowable.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    fun <Data> handleProgress(flowable: Flowable<Data>, isRefresh: Boolean): Flowable<Data> {
        return flowable
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                if (!isRefresh) {
                    getView()?.showProgress()
                }
            }.doOnNext {
                getView()?.hideProgress()
            }
            .observeOn(Schedulers.io())
    }

    //    inline fun <reified ReifiedType : ViewType> instantiateDummyView(): ReifiedType {
//        return ReifiedType::class.java.getConstructor().newInstance()
//    }

}