package com.example.data.mapper

import com.example.data.dto.BookDTO
import com.example.domain.entity.BookEntity
import javax.inject.Inject

class BookEntityMapper @Inject constructor() {
    fun mapToBookEntity(book: BookDTO): BookEntity =
        BookEntity(
            documents = mapToDocumentEntityList(book.documents),
            meta = mapToProgramEntityList(book.meta)
        )

    private fun mapToDocumentEntityList(
        bookDocumentList: List<BookDTO.BookDocument>,
    ): List<BookEntity.BookDocumentEntity> =
        bookDocumentList.map { document ->
            BookEntity.BookDocumentEntity(
                title = document.title,
                contents = document.contents,
                url = document.url,
                datetime = document.datetime,
                authors = document.authors,
                publisher = document.publisher,
                translators = document.translators,
                price = document.price,
                salePrice = document.salePrice,
                thumbnail = document.thumbnail,
                status = document.status
            )
        }

    private fun mapToProgramEntityList(
        metaData: BookDTO.MetaData,
    ): BookEntity.MetaDataEntity =
        BookEntity.MetaDataEntity(
            isEnd = metaData.isEnd,
            pageableCount = metaData.pageableCount,
            totalCount = metaData.totalCount
        )
}