package com.example.compose_booksearch.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_booksearch.BookViewModel
import com.example.compose_booksearch.R
import com.example.compose_booksearch.UiEvent
import com.example.compose_booksearch.uimodel.BookUiModel

@Composable
internal fun SearchBookScreen(
    onClickBookItem:(BookUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: BookViewModel = hiltViewModel()
    val onEvent = viewModel::onEvent

    val bookList by viewModel.bookList.collectAsState()

    var inputText by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_8dp)),
        modifier = modifier
    ) {
        SearchBarItem(
            inputText = inputText,
            onValueChange = { inputText = it },
            onClickSearchBtn = { keyword -> onEvent(UiEvent.SearchBook(keyword)) },
            onClickClearBtn = { inputText = ""}
        )

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
