package com.example.data.datasource.remote

import com.example.data.dto.BookDTO

interface BookRemoteDataSource {
    suspend fun searchBooksByName(keyword: String, page: Int, pageSize: Int): BookDTO
}