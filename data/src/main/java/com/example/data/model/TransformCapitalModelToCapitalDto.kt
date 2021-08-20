package com.example.data.model

import com.example.domain.dto.CapitalDto


class TransformCapitalModelToCapitalDto:  Transformer<CapitalModel,CapitalDto>{
    override fun transform(item: CapitalModel?): CapitalDto {
        val capitalDto = CapitalDto()
        item?.let {
            capitalDto.capital = it.capital ?: ""
        }
        return capitalDto
    }
}