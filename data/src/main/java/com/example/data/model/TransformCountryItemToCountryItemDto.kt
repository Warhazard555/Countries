package com.example.data.model

import com.example.domain.dto.CountryItemDto
import com.example.domain.dto.Language
import com.example.task1.convertToList


class TransformCountryItemToCountryItemDto : Transformer<CountryItem, CountryItemDto> {

    override fun transform(item: CountryItem?): CountryItemDto {
        val countryDto = CountryItemDto()
        item?.let {
            countryDto.name = it.name ?: ""
            countryDto.capital = it.capital ?: ""
            countryDto.area = it.area ?: 0F
            countryDto.population = it.population ?: -1
            countryDto.flag = it.flag ?: ""
            countryDto.latlng = it.latlng ?: arrayListOf(1.0, 1.0)
            countryDto.languages = (it.languages?.convertToList() ?: "") as List<Language>
        }
        return countryDto
    }
}