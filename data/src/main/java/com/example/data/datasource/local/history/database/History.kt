package com.example.data.datasource.local.history.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "History")
data class History(
    @ColumnInfo(name = "bookTitle") val bookTitle: String
) {
    @PrimaryKey(autoGenerate = true) var id: Int = 0
}

