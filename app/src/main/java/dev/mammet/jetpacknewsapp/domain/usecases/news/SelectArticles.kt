package dev.mammet.jetpacknewsapp.domain.usecases.news

import dev.mammet.jetpacknewsapp.data.local.NewsDao
import dev.mammet.jetpacknewsapp.domain.models.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao
) {

    operator fun invoke():Flow<List<Article>>{
        return newsDao.getArticles()
    }
}