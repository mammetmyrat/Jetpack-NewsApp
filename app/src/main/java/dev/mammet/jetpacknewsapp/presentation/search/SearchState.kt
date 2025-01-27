package dev.mammet.jetpacknewsapp.presentation.search

import androidx.paging.PagingData
import dev.mammet.jetpacknewsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery: String = "",
    val articles: Flow<PagingData<Article>>? = null
)
