package com.example.task1.fragments.countryList

import androidx.lifecycle.SavedStateHandle
import com.example.task1.DEBOUNCE_TIME_MILLIS
import com.example.task1.MIN_SEARCH_STRING_LENGTH
import com.example.task1.base.mvvm.BaseViewModel
import com.example.task1.base.mvvm.Outcome
import com.example.task1.base.mvvm.executeJob
import com.example.task1.convertToList
import com.example.task1.data.CountryItem
import com.example.task1.retrofit.RetrofitService
import com.example.task1.room.CountryApp
import com.example.task1.room.TableModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class BlankFragmentViewModel(savedStateHandle: SavedStateHandle) : BaseViewModel(savedStateHandle) {
    val mCountryLiveData =
        savedStateHandle.getLiveData<Outcome<MutableList<CountryItem>>>("CountryItem")
    val mSearchSubject: BehaviorSubject<String> = BehaviorSubject.create<String>()

    fun getCountryByName() {
        mCompositeDisposable.add(
            executeJob(
                RetrofitService.getInstance().getData(),
                mCountryLiveData
            )
        )
    }

    fun getCountryDB(
        response: MutableList<CountryItem>
    ) {
        val list: MutableList<TableModel> = mutableListOf()
        response.let {
            response.forEach { item ->
                list.add(
                    TableModel(
                        item.name,
                        item.capital,
                        item.area,
                        item.languages.convertToList(),
                        item.population
                    )
                )
            }
        }
        CountryApp.countryDao?.insertDatabase(list)

    }

    fun getSearchSubject(): @NonNull Observable<MutableList<CountryItem>>? = mSearchSubject
        .filter { it.length >= MIN_SEARCH_STRING_LENGTH }
        .debounce(DEBOUNCE_TIME_MILLIS, TimeUnit.MILLISECONDS)
        .distinctUntilChanged()
        .map { it.lowercase() }
        .flatMap {
            RetrofitService.getInstance().getCountryByName(it).toObservable()
                .onErrorResumeNext { Observable.just(mutableListOf()) }
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}