package com.example.domain.entity

data class BookEntity(
    val documents: List<BookDocumentEntity>,
    val meta: MetaDataEntity
) {
    data class BookDocumentEntity(
        val title: String,
        val contents: String,
        val url: String,
        val datetime: String,
        val authors: List<String>,
        val publisher: String,
        val translators: List<String>,
        val price: Int,
        val salePrice: Int,
        val thumbnail: String,
        val status: String
    )

    data class MetaDataEntity(
        val isEnd: Boolean,
        val pageableCount: Int,
        val totalCount: Int
    )
}
