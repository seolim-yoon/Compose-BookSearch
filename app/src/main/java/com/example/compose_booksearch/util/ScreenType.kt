package com.example.compose_booksearch.util

import kotlinx.serialization.Serializable

sealed interface ScreenType {
    @Serializable
    data object MainScreen: ScreenType

    @Serializable
    data class DetailScreen(
        val id: Int
    ): ScreenType
}