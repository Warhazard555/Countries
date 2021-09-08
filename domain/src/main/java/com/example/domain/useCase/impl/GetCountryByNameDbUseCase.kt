package com.example.domain.useCase.impl

import com.example.domain.dto.CountryItemDto
import com.example.domain.repository.DataBaseRepository
import com.example.domain.useCase.BaseUseCase
import io.reactivex.rxjava3.core.Flowable

class GetCountryByNameDbUseCase (private val dataBaseRepository: DataBaseRepository) : BaseUseCase<String, CountryItemDto>() {


    override val mIsParamsRequired: Boolean
        get() = true

    override fun buildFlowable(params: String?): Flowable<CountryItemDto> =
        dataBaseRepository.getCountryByNameDB(params ?: "")
}