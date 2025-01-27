package dev.mammet.jetpacknewsapp.domain.usecases.news

import dev.mammet.jetpacknewsapp.data.local.NewsDao
import dev.mammet.jetpacknewsapp.domain.models.Article

class UpsertArticle(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article){
        newsDao.upsert(article)
    }
}