package com.example.compose_booksearch.ui.book.screen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.compose_booksearch.ui.book.BookViewModel
import com.example.compose_booksearch.util.ScreenType

@Composable
internal fun NavHostScreen(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = ScreenType.MainScreen,
        modifier = modifier
    ) {
        composable<ScreenType.MainScreen> {
            val viewModel: BookViewModel = hiltViewModel()
            val state by viewModel.state.collectAsStateWithLifecycle()

            SearchBookScreen(
                state = state,
                onEvent = viewModel::onEvent,
                effectFlow = viewModel.effect,
                onNavigationRequested = { effect ->
                    navController.navigate(
                        route = ScreenType.DetailScreen(
                            id = effect.bookId
                        )
                    )
                }
            )
        }

        composable<ScreenType.DetailScreen> {
            val viewModel: BookViewModel = hiltViewModel(
                viewModelStoreOwner = navController.getBackStackEntry(ScreenType.MainScreen)
            )
            val state by viewModel.state.collectAsStateWithLifecycle()
            val bookId = it.toRoute<ScreenType.DetailScreen>().id

            state.bookList.find { it.id == bookId }?.let { bookUiModel ->
                DetailScreen(
                    book = bookUiModel,
                    onEvent = viewModel::onEvent,
                )
            }
        }
    }
}