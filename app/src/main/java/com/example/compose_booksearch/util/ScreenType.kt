package com.example.compose_booksearch.util

import kotlinx.serialization.Serializable

sealed interface ScreenType {
    @Serializable
    data object MainScreen: ScreenType

    @Serializable
    data class DetailScreen(
        val imageUrl: String,
        val title: String,
        val authors: String,
        val contents: String
    ): ScreenType
}