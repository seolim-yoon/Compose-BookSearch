package com.example.compose_booksearch.ui.event

sealed interface UiEvent {
    data class SearchBook(val keyword: String) : UiEvent
    data class ClickFavorite(val bookId: Int): UiEvent
    data object Refresh : UiEvent
    data object LoadMore : UiEvent
}
