package dev.mammet.jetpacknewsapp.domain.repository

import androidx.paging.PagingData
import dev.mammet.jetpacknewsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    fun getNews(sources: List<String>): Flow<PagingData<Article>>

    fun searchNews(searchQuery: String,sources: List<String>): Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)

    suspend fun deleteArticle(article: Article)

    suspend fun selectArticle(url: String):Article?

    fun selectArticles():Flow<List<Article>>
}