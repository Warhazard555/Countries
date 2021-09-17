package com.example.data.model

import com.example.data.model.news.Article
import com.example.domain.dto.NewsDto
import com.example.domain.outcome.Transformer

class TransformNewsDataToNewsDto: Transformer<List<Article>,List<NewsDto>> {
    override var convert:(List<Article>) -> List<NewsDto> =
        { item ->
            item.map {
                NewsDto(
                     tittle = it.title ?: "",
                 description = it.description ?: "",
                 url = it.url ?: "",
                publishedAt = it.publishedAt ?: "",
               urlToImage = it.urlToImage ?: "",
              author = it.author ?: ""
                )
            }
        }

}