package com.example.data.datasource.local.history.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HistoryDao {

    @Query("SELECT * FROM History")
    fun getAllHistory(): Flow<List<History>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertHistory(history: History)

    @Delete
    fun deleteHistory(history: History)

    @Query("DELETE FROM History")
    fun deleteAll()
}