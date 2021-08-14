package com.example.task1.data

class CountryItemDto (
    var name: String = "",
    var capital: String = "",
    var area: Float = 0F,
    var flag: String = "",
    var languages: List<Language> = arrayListOf(),
    var latlng: List<Double> = arrayListOf(1.0,1.0),
    var population: Int = 0
        )
