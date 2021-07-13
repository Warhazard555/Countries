package com.example.task1.data

import com.google.gson.annotations.SerializedName

data class CountryItem(
    val alpha2Code: String,
    val alpha3Code: String,
    val altSpellings: List<String>,
    @SerializedName("area")
    var area: Double,
    val borders: List<String>,
    val callingCodes: List<String>,
    @SerializedName("capital")
    var capital: String,
    val cioc: String,
    val currencies: List<Currency>,
    val demonym: String,
    val flag: String,
    val gini: Double,
    @SerializedName("languages")
    var languages: List<Language>,
    val latlng: List<Double>,
    @SerializedName("name")
    var name: String,
    val nativeName: String,
    val numericCode: String,
    val population: Int,
    val region: String,
    val regionalBlocs: List<RegionalBloc>,
    val subregion: String,
    val timezones: List<String>,
    val topLevelDomain: List<String>,
    val translations: Translations
)







