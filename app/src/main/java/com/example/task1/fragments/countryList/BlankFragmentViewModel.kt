package com.example.task1.fragments.countryList

import androidx.lifecycle.SavedStateHandle
import com.example.data.model.TableModel
import com.example.data.model.convertToDTO
import com.example.data.retrofit.RetrofitService
import com.example.domain.dto.CountryItemDto
import com.example.domain.repository.DataBaseRepository
import com.example.domain.repository.NetworkRepository
import com.example.task1.DEBOUNCE_TIME_MILLIS
import com.example.task1.MIN_SEARCH_STRING_LENGTH
import com.example.task1.base.mvvm.BaseViewModel
import com.example.task1.base.mvvm.Outcome
import com.example.task1.base.mvvm.executeJob
import com.example.task1.convertToList
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.annotations.NonNull
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.concurrent.TimeUnit

class BlankFragmentViewModel(
    savedStateHandle: SavedStateHandle,
    private val mNetworkRepository: NetworkRepository,
    private val mDataBaseRepository: DataBaseRepository
) : BaseViewModel(savedStateHandle) {
    val mCountryLiveData =
        savedStateHandle.getLiveData<Outcome<MutableList<CountryItemDto>>>("CountryItemDto")
    val mCountryDBLiveData =
        savedStateHandle.getLiveData<Outcome<MutableList<CountryItemDto>>>("CountryItemDB")
    val mSearchSubject: BehaviorSubject<String> = BehaviorSubject.create<String>()

    fun getCountryList() {
        mCompositeDisposable.add(
            executeJob(
                mNetworkRepository.getData(),
                mCountryLiveData
            )
        )
    }

    fun getCountryDB() {
        mCompositeDisposable.add(
            executeJob(
                mDataBaseRepository.getCountryDetals(), mCountryDBLiveData
            )
        )
    }

    //TODO: RX!!!
    fun addCountryDB(
        response: MutableList<CountryItemDto> = mutableListOf()
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
        mDataBaseRepository.insertDatabase(list.convertToDTO())

    }

    fun getSearchSubject(): @NonNull Observable<MutableList<CountryItemDto>>? = mSearchSubject
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