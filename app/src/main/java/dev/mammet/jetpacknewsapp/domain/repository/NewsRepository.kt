package dev.mammet.jetpacknewsapp.domain.repository

import androidx.paging.PagingData
import dev.mammet.jetpacknewsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>
}