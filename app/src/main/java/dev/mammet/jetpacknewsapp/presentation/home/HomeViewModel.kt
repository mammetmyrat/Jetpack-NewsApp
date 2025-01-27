package dev.mammet.jetpacknewsapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mammet.jetpacknewsapp.domain.usecases.news.NewsUseCase
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
):ViewModel() {

    val news = newsUseCase.getNews(
        sources = listOf("bbc-news","abc-news", "al-jazeera-english")
    ).cachedIn(viewModelScope)
}