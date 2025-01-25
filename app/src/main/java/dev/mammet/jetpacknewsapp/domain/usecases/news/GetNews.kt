package dev.mammet.jetpacknewsapp.domain.usecases.news

import androidx.paging.PagingData
import dev.mammet.jetpacknewsapp.domain.models.Article
import dev.mammet.jetpacknewsapp.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class GetNews(
    private val newsRepository: NewsRepository
) {

    operator fun invoke(sources: List<String>):Flow<PagingData<Article>>{
        return newsRepository.getNews(sources)
    }
}