package com.example.data.model

import com.example.domain.dto.LanguageDto


class TransformLanguageModelToLanguageDto : Transformer<LanguageModel, LanguageDto> {
    override fun transform(item: LanguageModel?): LanguageDto {
        val languageDto = LanguageDto()
        item?.let {
            languageDto.iso639_1 = it.iso639_1 ?: ""
            languageDto.iso639_2 = it.iso639_2 ?: ""
            languageDto.name = it.name ?: ""
            languageDto.nativeName = it.nativeName ?: ""


        }
        return languageDto
    }
}