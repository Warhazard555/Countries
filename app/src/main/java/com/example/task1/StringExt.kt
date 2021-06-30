package com.example.task1

fun List<Language>.convertToList(): String {
    var nameString: String = ""
    forEach { language ->
        nameString += "${language.name} "
    }
    return nameString
}
