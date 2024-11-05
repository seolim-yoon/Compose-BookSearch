package com.example.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookDTO(
    val documents: List<BookDocument> = listOf(),
    val meta: MetaData = MetaData()
) {
    @Serializable
    data class BookDocument(
        val title: String = "",
        val contents: String = "",
        val url: String = "",
        val isbn: String = "",
        val datetime: String = "",
        val authors: List<String> = listOf(),
        val publisher: String = "",
        val translators: List<String> = listOf(),
        val price: Int = 0,
        @SerialName("sale_price")
        val salePrice: Int = 0,
        val thumbnail: String = "",
        val status: String = ""
    )

    @Serializable
    data class MetaData(
        @SerialName("is_end")
        val isEnd: Boolean = false,
        @SerialName("pageable_count")
        val pageableCount: Int = 0,
        @SerialName("total_count")
        val totalCount: Int = 0
    )
}
