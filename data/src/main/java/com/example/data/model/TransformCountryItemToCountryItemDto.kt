package com.example.data.model

import com.example.domain.dto.CountryItemDto
import com.example.domain.dto.LanguageDto
import com.example.task1.convertToList


class TransformCountryItemToCountryItemDto : Transformer<CountryItem, CountryItemDto> {

    override fun transform(item: CountryItem?): CountryItemDto {
        val countryDto = CountryItemDto()
        item?.let {
            countryDto.name = it.name ?: ""
            countryDto.capital = it.capital ?: ""
            countryDto.area = it.area ?: 0F
            countryDto.population = it.population ?: -1
            countryDto.flags = it.flags?.get(0) ?: ""
            countryDto.latlng = (it.latlng ?: mutableListOf(1.0, 1.0)) as MutableList<Double>
            countryDto.languages = (it.languages?.convertToList() ?: "") as List<LanguageDto>
            countryDto.currentDistance = 0
        }
        return countryDto
    }
}