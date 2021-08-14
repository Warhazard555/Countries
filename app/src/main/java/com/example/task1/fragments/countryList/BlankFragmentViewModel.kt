package com.example.task1.fragments.countryList

import androidx.lifecycle.SavedStateHandle
import com.example.task1.DEBOUNCE_TIME_MILLIS
import com.example.task1.MIN_SEARCH_STRING_LENGTH
import com.example.task1.base.mvvm.BaseViewModel
import com.example.task1.base.mvvm.Outcome
import com.example.task1.base.mvvm.executeJob
import com.example.task1.convertToList
import com.example.task1.data.CountryItemDto
import com.example.task1.repository.database.DataBaseRepository
import com.example.task1.repository.network.NetworkRepository
import com.example.task1.retrofit.RetrofitService
import com.example.task1.room.TableModel
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

    fun getCountryByName() {
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

    fun addCountryDB(
        response: MutableList<CountryItemDto>
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
        mDataBaseRepository.insertDatabase(list)

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