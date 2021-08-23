package com.example.data.model

import java.io.Serializable

data class LanguageModel(
    var iso639_1: String?,
    var iso639_2: String?,
    var name: String?,
    var nativeName: String?
) : Serializable