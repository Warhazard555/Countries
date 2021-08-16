package com.example.domain.useCase.impl

import com.example.domain.dto.CountryItemDto
import com.example.domain.repository.DataBaseRepository
import com.example.domain.useCase.BaseUseCase
import io.reactivex.rxjava3.core.Flowable

class InsertDBUseCase(private val dataBaseRepository: DataBaseRepository) : BaseUseCase<List<CountryItemDto>, MutableList<CountryItemDto>>() {
    override val mIsParamsRequired: Boolean
        get() = true

    override fun buildFlowable(params: List<CountryItemDto>?): Flowable<MutableList<CountryItemDto>> = dataBaseRepository.insertDatabase(params ?: listOf())









}