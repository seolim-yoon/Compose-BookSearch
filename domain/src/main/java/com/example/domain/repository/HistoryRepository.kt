package com.example.domain.repository

import com.example.domain.entity.HistoryEntity
import kotlinx.coroutines.flow.Flow

interface HistoryRepository {
    suspend fun getAllHistory(): Flow<List<HistoryEntity>>

    suspend fun insertHistory(bookTitle: String)

    suspend fun deleteHistory(historyEntity: HistoryEntity)

    suspend fun deleteAll()
}