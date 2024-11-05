package com.example.domain.repository

import com.example.domain.entity.BookEntity

interface BookRepository {
    suspend fun searchBooksByName(keyword: String, page: Int, pageSize: Int): BookEntity
}