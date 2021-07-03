package com.example.task1

import com.example.task1.data.Language

fun List<Language>.convertToList(): String {
    var nameString: String = ""
    forEach { language ->
        nameString += "${language.name} "
    }
    return nameString
}
