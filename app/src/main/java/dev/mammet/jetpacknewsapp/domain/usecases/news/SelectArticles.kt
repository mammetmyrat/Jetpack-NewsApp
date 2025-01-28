package dev.mammet.jetpacknewsapp.domain.usecases.news

import dev.mammet.jetpacknewsapp.data.local.NewsDao
import dev.mammet.jetpacknewsapp.domain.models.Article
import dev.mammet.jetpacknewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {

    operator fun invoke():Flow<List<Article>>{
        return newsRepository.selectArticles()
    }
}