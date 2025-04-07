package com.example.data.datasource.remote

import com.example.data.datasource.remote.api.BookServiceApi
import com.example.data.dto.BookDTO
import javax.inject.Inject

class BookRemoteDataSourceImpl @Inject constructor(
    private val boorServiceApi: BookServiceApi
) : BookRemoteDataSource {
    override suspend fun searchBooksByName(
        keyword: String,
        page: Int,
        pageSize: Int
    ): BookDTO = boorServiceApi.searchBooksByName(
        keyword = keyword,
        page = page,
        pageSize = pageSize
    )
}