package com.example.task1.fragments.capitalFragment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.domain.dto.CapitalDto
import com.example.domain.outcome.Outcome
import com.example.domain.repository.NetworkCapitalsFlowRepository
import com.example.domain.useCase.impl.GetAllCapitalUseCase
import com.example.task1.DEBOUNCE_TIME_MILLIS
import com.example.task1.MIN_SEARCH_STRING_LENGTH
import com.example.task1.base.mvvm.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


class CapitalFragmentViewModel(
    savedStateHandle: SavedStateHandle,
    private val getAllCapitalUseCase: GetAllCapitalUseCase,
    private val mNetworkCapitalsFlowRepository: NetworkCapitalsFlowRepository
) : BaseViewModel(savedStateHandle) {

    var sharedFlow: MutableSharedFlow<Long> = MutableSharedFlow()
    var searchFlow = MutableStateFlow("")

    val capitalLiveData =
        savedStateHandle.getLiveData<Outcome<MutableList<CapitalDto>>>("CapitalDto")

//    fun getAllCapital() {
//        CoroutineScope(viewModelScope.coroutineContext).launch {
//            try {
//                capitalLiveData.value = Outcome.loading(true)
//                val result = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
//                    getAllCapitalUseCase.execute()
//                }
//                capitalLiveData.value = Outcome.loading(false)
//                capitalLiveData.value = Outcome.success(result)
//            } catch (e: Exception) {
//                capitalLiveData.value = Outcome.loading(false)
//                capitalLiveData.value = Outcome.failure(e)
//            }
//        }
//    }

    fun getAllCapitalFlow(): Flow<Outcome<MutableList<CapitalDto>>> =
        mNetworkCapitalsFlowRepository.getAllCapital()

    fun capitalItemClickFlow() {
        viewModelScope.launch {
            getAllCapitalFlow().collect {
                if (it is Outcome.Success<MutableList<CapitalDto>>) {
                    sharedFlow.emit(it.data.size.toLong())
                }
            }
        }
    }

    fun getSearchSubjectFlow() {
        viewModelScope.launch {
            searchFlow
                .filter { it.length >= MIN_SEARCH_STRING_LENGTH }
                .debounce(DEBOUNCE_TIME_MILLIS)
                .distinctUntilChanged()
                .map { it.lowercase() }
                .flatMapLatest {
                    mNetworkCapitalsFlowRepository.getCapitalByName(it)
                }
                .catch { emitAll(flowOf()) }
                .flowOn(Dispatchers.IO)
                .collect {
                    capitalLiveData.value = it

                }
        }
    }
}


