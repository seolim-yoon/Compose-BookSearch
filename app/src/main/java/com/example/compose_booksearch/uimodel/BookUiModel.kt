package com.example.compose_booksearch.uimodel

data class BookUiModel(
    val id: Int,
    val title: String,
    val contents: String,
    val url: String,
    val authors: String,
    val publisher: String,
    val price: Int,
    val salePrice: Int,
    val thumbnail: String,
    val status: String,
    val isFavorite: Boolean
)

data class SearchResultUiModel(
    val bookList: List<BookUiModel> = listOf(),
    val totalCount: Int = 0
)

