package dev.mammet.jetpacknewsapp.domain.usecases.news

import dev.mammet.jetpacknewsapp.data.local.NewsDao
import dev.mammet.jetpacknewsapp.domain.models.Article
import dev.mammet.jetpacknewsapp.domain.repository.NewsRepository

class SelectArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(url:String):Article?{
        return newsRepository.selectArticle(url)
    }
}