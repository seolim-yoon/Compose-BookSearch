package com.example.data.repository

import com.example.data.datasource.local.book.BookLocalDataSource
import com.example.data.datasource.remote.BookRemoteDataSource
import com.example.data.dto.BookDTO
import javax.inject.Inject

class BookRepository @Inject constructor(
    private val bookLocalDataSource: BookLocalDataSource,
    private val bookRemoteDataSource: BookRemoteDataSource
) {
    suspend fun searchBooksByName(
        keyword: String,
        page: Int,
        pageSize: Int
    ): BookDTO =
        bookRemoteDataSource.searchBooksByName(
            keyword = keyword,
            page = page,
            pageSize = pageSize
        )
}