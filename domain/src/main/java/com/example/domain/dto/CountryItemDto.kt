package com.example.domain.dto


import java.io.Serializable

class CountryItemDto(
    var name: String = "",
    var capital: String = "",
    var area: Float = 0F,
    var flag: String = "",
    var languages: List<LanguageDto> = arrayListOf(),
    var latlng: MutableList<Double> = arrayListOf(1.0, 1.0),
    var population: Int = 0,
    var currentDistance: Int = 0
) : Serializable
