package dev.mammet.jetpacknewsapp.presentation.details

import dev.mammet.jetpacknewsapp.domain.models.Article

sealed class DetailsEvent {

    data class UpsertDeleteArticle(val article: Article): DetailsEvent()

    object RemoveSideEffect: DetailsEvent()
}