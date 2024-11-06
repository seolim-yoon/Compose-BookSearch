package com.example.compose_booksearch.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
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
            MainScreen(
                onClickBookItem = { book ->
                    navController.navigate(
                        ScreenType.DetailScreen(
                            imageUrl = book.thumbnail,
                            title = book.title,
                            authors = book.authors.toString(),
                            contents = book.contents
                        )
                    )
                }
            )
        }
        composable<ScreenType.DetailScreen> {
            DetailScreen(
                book = it.toRoute<ScreenType.DetailScreen>()
            )
        }
    }
}