package dev.mammet.jetpacknewsapp.presentation.bookmark

import dev.mammet.jetpacknewsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

data class BookmarkState(
    val articles: List<Article> = emptyList()
)