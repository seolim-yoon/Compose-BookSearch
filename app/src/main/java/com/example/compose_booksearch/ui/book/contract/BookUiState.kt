package com.example.compose_booksearch.ui.book.contract

import com.example.compose_booksearch.base.LoadState
import com.example.compose_booksearch.base.UiState
import com.example.compose_booksearch.uimodel.BookUiModel

data class BookUiState(
    val loadState: LoadState = LoadState.Success,
    val totalCount: Int = 0,
    val bookList: List<BookUiModel> = emptyList()
) : UiState