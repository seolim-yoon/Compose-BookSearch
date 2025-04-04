package com.example.compose_booksearch.ui.book.contract

import com.example.compose_booksearch.base.UiEffect

sealed interface BookUiEffect: UiEffect {
    data class NavigateToDetail(val bookId: Int): BookUiEffect
    data class Toast(val msg: String): BookUiEffect
}