package com.example.domain.useCase.impl

import com.example.domain.dto.CountryItemDto
import com.example.domain.repository.DataBaseRepository
import com.example.domain.useCase.BaseUseCase
import io.reactivex.rxjava3.core.Flowable

class GetAllCountryDbUseCase(private val dataBaseRepository: DataBaseRepository) :
    BaseUseCase<Unit, MutableList<CountryItemDto>>() {

    override val mIsParamsRequired: Boolean
        get() = false

    override fun buildFlowable(params: Unit?): Flowable<MutableList<CountryItemDto>> =
        dataBaseRepository.getAllCountryDB()

}