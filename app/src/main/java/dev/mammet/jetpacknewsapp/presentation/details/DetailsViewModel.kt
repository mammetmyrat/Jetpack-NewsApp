package dev.mammet.jetpacknewsapp.presentation.details

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.mammet.jetpacknewsapp.domain.models.Article
import dev.mammet.jetpacknewsapp.domain.usecases.news.NewsUseCase
import dev.mammet.jetpacknewsapp.utils.UIComponent
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val newsUseCase: NewsUseCase
) : ViewModel() {

    var sideEffect by mutableStateOf<UIComponent?>(null)
        private set


    fun onEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.UpsertDeleteArticle -> {
                viewModelScope.launch {
                    val article = newsUseCase.selectArticle(event.article.url)
                    if (article == null) {
                        upsertArticle(event.article)
                    } else {
                        deleteArticle(event.article)
                    }

                }
            }
            is DetailsEvent.RemoveSideEffect ->{
                sideEffect = null
            }
        }
    }

    private suspend fun deleteArticle(article: Article) {
        newsUseCase.deleteArticle(article)
        sideEffect = UIComponent.Toast("Article deleted")

    }

    private suspend fun upsertArticle(article: Article) {
        newsUseCase.upsertArticle(article)
        sideEffect = UIComponent.Toast("Article Inserted")
    }

}