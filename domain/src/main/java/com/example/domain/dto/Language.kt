package com.example.domain.dto

import java.io.Serializable

data class Language(
    val iso639_1: String?,
    val iso639_2: String?,
    val name: String?,
    val nativeName: String?
) : Serializable