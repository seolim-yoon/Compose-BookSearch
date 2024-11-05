package com.example.compose_booksearch.mapper

import com.example.compose_booksearch.uimodel.BookUiModel
import com.example.domain.entity.BookEntity
import javax.inject.Inject

class BookUiMapper @Inject constructor() {
    fun mapToBookUiModelList(document: BookEntity): List<BookUiModel> =
        document.documents.mapIndexed { index, book ->
            BookUiModel(
                id = index,
                title = book.title,
                contents = book.contents,
                url = book.url,
                authors = book.authors,
                publisher = book.publisher,
                price = book.price,
                salePrice = book.salePrice,
                thumbnail = book.thumbnail,
                status = book.status
            )
        }
}