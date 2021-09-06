package com.example.domain.useCase.impl

import com.example.domain.dto.CountryItemDto
import com.example.domain.repository.NetworkRepository
import com.example.domain.useCase.BaseUseCase
import io.reactivex.rxjava3.core.Flowable

class GetAllCountryUseCase(private val networkRepository: NetworkRepository) :
    BaseUseCase<Unit, MutableList<CountryItemDto>>() {

    override fun buildFlowable(params: Unit?): Flowable<MutableList<CountryItemDto>> =
        networkRepository.getData()

    override val mIsParamsRequired: Boolean
        get() = false
}