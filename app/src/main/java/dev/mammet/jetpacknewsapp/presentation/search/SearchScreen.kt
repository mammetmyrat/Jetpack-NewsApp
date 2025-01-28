package dev.mammet.jetpacknewsapp.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.paging.compose.collectAsLazyPagingItems
import dev.mammet.jetpacknewsapp.domain.models.Article
import dev.mammet.jetpacknewsapp.presentation.Dimens.MediumPadding1
import dev.mammet.jetpacknewsapp.presentation.commons.ArticlesList
import dev.mammet.jetpacknewsapp.presentation.commons.SearchBar
import dev.mammet.jetpacknewsapp.presentation.navgraph.Route

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    state: SearchState,
    event: (SearchEvent) -> Unit,
    navigateToDetails: (Article) -> Unit
) {

    Column(
        modifier = modifier
            .padding(
                top = MediumPadding1,
                start = MediumPadding1,
                end = MediumPadding1
            )
            .statusBarsPadding()
            .fillMaxSize()
    ) {

        SearchBar(
            text = state.searchQuery,
            readOnly = false,
            onValueChange = { query -> event(SearchEvent.UpdateSearchQuery(query)) },
            onSearch = { event(SearchEvent.SearchNews) }
        )
        Spacer(modifier = Modifier.height(MediumPadding1))
        state.articles?.let { notNullArticles ->
            val articles = notNullArticles.collectAsLazyPagingItems()
            ArticlesList(
                articles = articles,
                onClick = { navigateToDetails(it) }
            )

        }

    }

}