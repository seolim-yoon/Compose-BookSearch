package com.example.data.api

import com.example.data.dto.BookDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface BookServiceApi {
    @GET("search/book")
    suspend fun searchBooksByName(
        @Query("query") keyword: String,
        @Query("page") page: Int,
        @Query("size") pageSize: Int
    ): BookDTO
}