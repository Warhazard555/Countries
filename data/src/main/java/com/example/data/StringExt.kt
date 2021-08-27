package com.example.task1

import com.example.domain.dto.Language

fun List<Language>.convertToList(): String {
    var nameString: String = ""
    forEach { language ->
        nameString += " ${language.name}; "
    }
    return nameString
}
