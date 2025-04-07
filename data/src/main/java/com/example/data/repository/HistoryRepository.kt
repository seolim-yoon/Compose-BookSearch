package com.example.data.repository

import com.example.data.datasource.local.history.HistoryLocalDataSource
import com.example.data.datasource.local.history.database.History
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HistoryRepository @Inject constructor(
    private val historyLocalDataSource: HistoryLocalDataSource
) {
    suspend fun getAllHistory(): Flow<List<History>> = historyLocalDataSource.getAllHistory()

    suspend fun insertHistory(history: History) = historyLocalDataSource.insertHistory(history)

    suspend fun deleteHistory(history: History) = historyLocalDataSource.deleteHistory(history)

    suspend fun deleteAll() = historyLocalDataSource.deleteAll()
}