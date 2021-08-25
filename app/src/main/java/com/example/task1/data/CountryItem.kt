package com.example.task1.data

import com.google.gson.annotations.SerializedName

data class CountryItem(
    @SerializedName("name")
    var name: String,
    @SerializedName("capital")
    var capital: String,
    @SerializedName("area")
    var area: Float,
    @SerializedName("flag")
    val flag: String,
    @SerializedName("languages")
    var languages: List<Language>,
    @SerializedName("latlng")
    var latlng: List<Double>,
    @SerializedName("population")
    val population: Int
//    val alpha2Code: String,
//    val alpha3Code: String,
//    val altSpellings: List<String>,
//    val borders: List<String>,
//    val callingCodes: List<String>,
//    val cioc: String,
//    val currencies: List<Currency>,
//    val demonym: String,
//    val gini: Double,
//    val nativeName: String,
//    val numericCode: String,
//    val region: String,
//    val regionalBlocs: List<RegionalBloc>,
//    val subregion: String,
//    val timezones: List<String>,
//    val topLevelDomain: List<String>,
//    val translations: Translations
)







