package com.example.compose_booksearch.mapper

import com.example.compose_booksearch.uimodel.HistoryUiModel
import com.example.data.datasource.local.history.database.History
import javax.inject.Inject

class HistoryUiMapper @Inject constructor() {
    fun mapToHistoryUiModel(history: History): HistoryUiModel =
        HistoryUiModel(
            id = history.id,
            bookTitle = history.bookTitle
        )

    fun mapToHistory(historyUiModel: HistoryUiModel): History =
        History(
            bookTitle = historyUiModel.bookTitle
        )
}
