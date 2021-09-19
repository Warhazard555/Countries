package com.example.task1.fragments.countryList

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import com.example.data.model.TableModel
import com.example.data.model.convertToDTO
import com.example.domain.dto.CountryItemDto
import com.example.domain.outcome.Outcome
import com.example.domain.repository.DataBaseRepository
import com.example.domain.useCase.impl.GetAllCountryDbUseCase
import com.example.domain.useCase.impl.GetAllCountryUseCase
import com.example.domain.useCase.impl.GetCountryByNameUseCase
import com.example.task1.DEBOUNCE_TIME_MILLIS
import com.example.task1.MIN_SEARCH_STRING_LENGTH
import com.example.task1.base.mvvm.*
import com.example.task1.ext.distanceFromMyLocation
import com.example.task1.ext.lastLocation
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class CountryListViewModel(
    savedStateHandle: SavedStateHandle,
    private val mDataBaseRepository: DataBaseRepository,
    private val getAllCountryUseCase: GetAllCountryUseCase,
    private val getCountryByNameUseCase: GetCountryByNameUseCase,
    private val getAllCountryDbUseCase: GetAllCountryDbUseCase,

) : BaseViewModel(savedStateHandle) {
    val mCountryLiveData =
        savedStateHandle.getLiveData<Outcome<MutableList<CountryItemDto>>>("CountryItemDto")
    val mCountryDBLiveData =
        savedStateHandle.getLiveData<Outcome<MutableList<CountryItemDto>>>("CountryItemDB")
    val mSearchSubject: BehaviorSubject<String> = BehaviorSubject.create<String>()

    fun getCountryList(context: Context) {
//        mCompositeDisposable.add(
//            executeJob(
//                getAllCountryUseCase.execute(),
//                mCountryLiveData
//            )
//        )
        Flowable.just(context)
            .observeOn(AndroidSchedulers.mainThread())
            .flatMap { lastLocation(context = it) }
            .observeOn(Schedulers.io())
            .flatMap {
                getAllCountryUseCase.execute()
            }
            .doOnNext {
                it.forEach { item ->
                    item.currentDistance = distanceFromMyLocation(item.latlng)
                }
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                mCountryLiveData.next(it)
            }, {
                mCountryLiveData.failed(it)
            }, {
                if (mCountryLiveData.value is Outcome.Next) {
                    mCountryLiveData.success((mCountryLiveData.value as Outcome.Next).data)
                }
            }).also { mCompositeDisposable.add(it) }
    }

    fun getCountryDB() {
        mCompositeDisposable.add(
            executeJob(
                getAllCountryDbUseCase.execute(), mCountryDBLiveData
            )
        )
    }

    fun addCountryDB(
        response: MutableList<CountryItemDto> = mutableListOf()
    ) {
        Flowable.just(response)
            .doOnNext {
                val list: MutableList<TableModel> = mutableListOf()
                response.let {
                    response.forEach { item ->
                        list.add(
                            TableModel(
                                item.name,
                                item.capital,
                                item.area,

                                item.flag,
                                item.population,
                                item.currentDistance
                            )
                        )
                    }
                }
                mDataBaseRepository.insertDatabase(list.convertToDTO())
            }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
    }

    fun getSearchSubject(): @NonNull Observable<MutableList<CountryItemDto>>? = mSearchSubject
        .filter { it.length >= MIN_SEARCH_STRING_LENGTH }
        .debounce(DEBOUNCE_TIME_MILLIS, TimeUnit.MILLISECONDS)
        .distinctUntilChanged()
        .map { it.lowercase() }
        .flatMap {
            getCountryByNameUseCase.setParams(it).execute().toObservable()
                .onErrorResumeNext { Observable.just(mutableListOf()) }
        }
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
}