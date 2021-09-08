package com.example.task1.fragments.capitalFragment

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.domain.dto.CapitalDto
import com.example.domain.outcome.Outcome
import com.example.domain.repository.NetworkCapitalsFlowRepository
import com.example.domain.useCase.impl.GetAllCapitalUseCase
import com.example.task1.base.mvvm.BaseViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class CapitalFragmentViewModel(
    savedStateHandle: SavedStateHandle,
    private val getAllCapitalUseCase: GetAllCapitalUseCase,
    private val mNetworkCapitalsFlowRepository: NetworkCapitalsFlowRepository
) : BaseViewModel(savedStateHandle) {

    val capitalLiveData =
        savedStateHandle.getLiveData<Outcome<MutableList<CapitalDto>>>("CapitalDto")

    fun getAllCapital() {
        CoroutineScope(viewModelScope.coroutineContext).launch {
            try {
                capitalLiveData.value = Outcome.loading(true)
                val result = withContext(viewModelScope.coroutineContext + Dispatchers.IO) {
                    getAllCapitalUseCase.execute()
                }
                capitalLiveData.value = Outcome.loading(false)
                capitalLiveData.value = Outcome.success(result)
            } catch (e: Exception) {
                capitalLiveData.value = Outcome.loading(false)
                capitalLiveData.value = Outcome.failure(e)
            }
        }
    }

    fun getAllCapitalFlow(): Flow<Outcome<List<CapitalDto>>> =
        mNetworkCapitalsFlowRepository.getAllCapital()


}