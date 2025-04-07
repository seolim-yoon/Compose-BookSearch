package com.example.compose_booksearch.mapper

import com.example.compose_booksearch.uimodel.BookUiModel
import com.example.compose_booksearch.uimodel.SearchResultUiModel
import com.example.data.dto.BookDTO
import javax.inject.Inject

class BookUiMapper @Inject constructor() {
    fun mapToSearchResultUiModel(document: BookDTO): SearchResultUiModel =
        SearchResultUiModel(
            bookList = mapToBookUiModelList(document.documents),
            totalCount = document.meta.totalCount

        )
    private fun mapToBookUiModelList(bookEntityList: List<BookDTO.BookDocument>): List<BookUiModel> =
        bookEntityList.mapIndexed { index, book ->
            BookUiModel(
                id = index,
                title = book.title,
                contents = book.contents,
                url = book.url,
                authors = book.authors.joinToString(", "),
                publisher = book.publisher,
                price = book.price,
                salePrice = book.salePrice,
                thumbnail = book.thumbnail,
                status = book.status,
                isFavorite = false
            )
        }
}