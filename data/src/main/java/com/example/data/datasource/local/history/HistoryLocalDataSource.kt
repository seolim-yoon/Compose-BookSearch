package com.example.data.datasource.local.history

import com.example.data.datasource.local.history.database.History
import kotlinx.coroutines.flow.Flow

interface HistoryLocalDataSource {
    suspend fun getAllHistory(): Flow<List<History>>

    suspend fun insertHistory(history: History)

    suspend fun deleteHistory(historyEntity: History)

    suspend fun deleteAll()
}