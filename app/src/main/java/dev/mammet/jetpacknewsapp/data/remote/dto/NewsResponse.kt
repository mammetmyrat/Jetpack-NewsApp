package dev.mammet.jetpacknewsapp.data.remote.dto

import dev.mammet.jetpacknewsapp.domain.models.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)