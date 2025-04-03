package com.example.compose_booksearch.ui.book.contract

import com.example.compose_booksearch.base.UiEvent

sealed interface BookUiEvent: UiEvent {
    data class SearchBook(val keyword: String) : BookUiEvent
    data class ClickFavorite(val bookId: Int): BookUiEvent
    data object Refresh : BookUiEvent
    data object LoadMore : BookUiEvent
}
