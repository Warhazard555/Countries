package com.example.domain.useCase.impl

import com.example.domain.dto.CountryItemDto
import com.example.domain.repository.NetworkRepository
import com.example.domain.useCase.BaseUseCase
import io.reactivex.rxjava3.core.Flowable

class GetCountryByNameUseCase(private val networkRepository: NetworkRepository) :
    BaseUseCase<String, MutableList<CountryItemDto>>() {

    override val mIsParamsRequired: Boolean
        get() = true

    override fun buildFlowable(params: String?): Flowable<MutableList<CountryItemDto>> =
        networkRepository.getCountryByName(params ?: "")


}