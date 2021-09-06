package com.example.domain.useCase.impl

import com.example.domain.dto.CapitalDto
import com.example.domain.repository.NetworkCapitalRepository
import com.example.domain.useCase.CoroutionesUseCase

class GetAllCapitalUseCase(private val capitalRepository: NetworkCapitalRepository) :
    CoroutionesUseCase<Unit, MutableList<CapitalDto>>() {
    override val mIsParamsRequired: Boolean
        get() = false

    override suspend fun buildResult(params: Unit?): MutableList<CapitalDto> =
        capitalRepository.getAllCapital()


}