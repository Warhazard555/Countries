package com.example.data.model

import com.example.domain.dto.CapitalDto
import com.example.domain.outcome.Transformer


class TransformCapitalModelToCapitalDto : Transformer<List<CapitalModel>, List<CapitalDto>> {
    override var convert: (List<CapitalModel>) -> List<CapitalDto> =
        { data ->
            data.map {
                CapitalDto(
                    capital = it.capital ?: ""
                )
            }
        }


}