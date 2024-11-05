package com.example.domain.usecase

import com.example.domain.entity.BookEntity
import com.example.domain.repository.BookRepository
import javax.inject.Inject

class SearchBookUseCase @Inject constructor(
    private val bookRepository: BookRepository
) {
    suspend operator fun invoke(
        keyword: String,
        page: Int,
        pageSize: Int
    ): BookEntity =
        bookRepository.searchBooksByName(
            keyword = keyword,
            page = page,
            pageSize = pageSize
        )
}