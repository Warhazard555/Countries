package com.example.domain.dto

import java.io.Serializable

data class NewsDto (
    var tittle: String = "",
    var description: String = "",
    var url: String = "",
    var publishedAt: String = "",
    var urlToImage: String = "",
    var author: String = ""
) : Serializable