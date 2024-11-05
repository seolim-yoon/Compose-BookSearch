package com.example.data.repository

import com.example.data.datasource.local.BookLocalDataSourceImpl
import com.example.data.datasource.remote.BookRemoteDataSourceImpl
import com.example.data.mapper.BookEntityMapper
import com.example.domain.entity.BookEntity
import com.example.domain.repository.BookRepository
import javax.inject.Inject

class BookRepositoryImpl @Inject constructor(
    private val bookLocalDataSourceImpl: BookLocalDataSourceImpl,
    private val bookRemoteDataSourceImpl: BookRemoteDataSourceImpl,
    private val bookEntityMapper: BookEntityMapper
) : BookRepository {
    override suspend fun searchBooksByName(
        keyword: String,
        page: Int,
        pageSize: Int
    ): BookEntity = bookEntityMapper.mapToBookEntity(
        bookRemoteDataSourceImpl.searchBooksByName(
            keyword = keyword,
            page = page,
            pageSize = pageSize
        )
    )
}