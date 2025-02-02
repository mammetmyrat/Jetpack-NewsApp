package dev.mammet.jetpacknewsapp.presentation.navgraph

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.paging.compose.collectAsLazyPagingItems
import dev.mammet.jetpacknewsapp.presentation.bookmark.BookmarkScreen
import dev.mammet.jetpacknewsapp.presentation.bookmark.BookmarkViewModel
import dev.mammet.jetpacknewsapp.presentation.home.HomeScreen
import dev.mammet.jetpacknewsapp.presentation.home.HomeViewModel
import dev.mammet.jetpacknewsapp.presentation.news_navigator.NewsNavigatorScreen
import dev.mammet.jetpacknewsapp.presentation.onboarding.OnBoardingScreen
import dev.mammet.jetpacknewsapp.presentation.onboarding.OnBoardingViewModel
import dev.mammet.jetpacknewsapp.presentation.search.SearchScreen
import dev.mammet.jetpacknewsapp.presentation.search.SearchViewModel

@Composable
fun NavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        navigation(
            route = Route.AppStartNavigation.route,
            startDestination = Route.OnBoardingScreen.route,
        ) {
            composable(
                route = Route.OnBoardingScreen.route
            ) {
                val viewModel: OnBoardingViewModel = hiltViewModel()
                OnBoardingScreen(
                    event = viewModel::onEvent
                )
            }
        }

        navigation(
            route = Route.NewsNavigation.route,
            startDestination = Route.NewsNavigatorScreen.route
        ) {
            composable(
                route = Route.NewsNavigatorScreen.route
            ) {
                NewsNavigatorScreen()
            }
        }
    }

}