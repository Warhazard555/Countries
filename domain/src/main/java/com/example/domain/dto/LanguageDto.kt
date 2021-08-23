package com.example.domain.dto

import java.io.Serializable

data class LanguageDto(
    var iso639_1: String = "",
    var iso639_2: String = "",
    var name: String = "",
    var nativeName: String = ""
) : Serializable