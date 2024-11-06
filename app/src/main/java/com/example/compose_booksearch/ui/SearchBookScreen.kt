package com.example.compose_booksearch.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.example.compose_booksearch.R
import com.example.compose_booksearch.uimodel.BookUiModel

@Composable
internal fun SearchBookScreen(
    totalCount: Int,
    bookList: List<BookUiModel>,
    onClickBookItem:(BookUiModel) -> Unit,
    onClickSearchBtn:(String) -> Unit,

    modifier: Modifier = Modifier
) {
    var keyword by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_8dp)),
        modifier = modifier
    ) {
        SearchBarItem(
            inputText = keyword,
            onValueChange = { keyword = it },
            onClickSearchBtn = onClickSearchBtn,
            onClickClearBtn = { keyword = "" }
        )

        if (totalCount > 0) {
            TotalCountItem(
                totalCount = totalCount
            )
        }

        LazyColumn(
            contentPadding = PaddingValues(dimensionResource(R.dimen.padding_16dp)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_8dp)),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(
                key = { it.id },
                items = bookList
            ) { book ->
                BookItem(
                    book = book,
                    onClickBookItem = onClickBookItem
                )
            }
        }
    }
}

