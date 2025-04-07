package com.example.data.datasource.local.history

import com.example.data.datasource.local.history.database.HistoryDao
import com.example.data.datasource.local.history.database.History
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class HistoryLocalDataSourceImpl @Inject constructor(
    private val historyDao: HistoryDao
): HistoryLocalDataSource {

    override suspend fun getAllHistory(): Flow<List<History>> = historyDao.getAllHistory()

    override suspend fun insertHistory(history: History) = historyDao.insertHistory(history)

    override suspend fun deleteHistory(historyEntity: History) = historyDao.deleteHistory(historyEntity)

    override suspend fun deleteAll() = historyDao.deleteAll()
}