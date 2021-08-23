package com.example.task1

import com.example.domain.dto.LanguageDto

fun List<LanguageDto>.convertToList(): String {
    var nameString: String = ""
    forEach { language ->
        nameString += " ${language.name}; "
    }
    return nameString
}
