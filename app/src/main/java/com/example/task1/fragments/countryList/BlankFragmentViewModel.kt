package com.example.task1.fragments.countryList

import androidx.lifecycle.SavedStateHandle
import com.example.data.model.TableModel
import com.example.data.model.convertToDTO
import com.example.domain.dto.CountryItemDto
import com.example.domain.repository.DataBaseRepository
import com.example.domain.useCase.impl.GetAllCountryDbUseCase
import com.example.domain.useCase.impl.GetAllCountryUseCase
import com.example.domain.useCase.impl.GetCountryByNameUseCase
import com.example.task1.DEBOUNCE_TIME_MILLIS
import com.example.task1.MIN_SEARCH_STRING_LENGTH
import com.example.task1.base.mvvm.BaseViewModel
import com.example.task1.base.mvvm.Outcome
import com.example.task1.base.mvvm.executeJob
import com.example.task1.convertToList
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class BlankFragmentViewModel(
    savedStateHandle: SavedStateHandle,
    private val mDataBaseRepository: DataBaseRepository,
    private val getAllCountryUseCase: GetAllCountryUseCase,
    private val getCountryByNameUseCase: GetCountryByNameUseCase,
    private val getAllCountryDbUseCase: GetAllCountryDbUseCase

) : BaseViewModel(savedStateHandle) {
    val mCountryLiveData =
        savedStateHandle.getLiveData<Outcome<MutableList<CountryItemDto>>>("CountryItemDto")
    val mCountryDBLiveData =
        savedStateHandle.getLiveData<Outcome<MutableList<CountryItemDto>>>("CountryItemDB")
    val mSearchSubject: BehaviorSubject<String> = BehaviorSubject.create<String>()


    //TODO: Добавить BD в цепочку RX
    fun getCountryList() {
        mCompositeDisposable.add(
            executeJob(
                getAllCountryUseCase.execute(),
                mCountryLiveData
            )
        )
    }

    fun getCountryDB() {
        mCompositeDisposable.add(
            executeJob(
                getAllCountryDbUseCase.execute(), mCountryDBLiveData
            )
        )
    }

    //TODO: RX!!!
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
                                item.languages.convertToList(),
                                item.population
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