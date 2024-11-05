package com.example.compose_booksearch.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_booksearch.BookViewModel
import com.example.compose_booksearch.R
import com.example.compose_booksearch.UiEvent
import com.example.compose_booksearch.uimodel.BookUiModel

@Composable
internal fun SearchBookScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: BookViewModel = hiltViewModel()
    val onEvent = viewModel::onEvent

    val keyword by viewModel.keyword.collectAsState()
    val bookList by viewModel.bookList.collectAsState()

    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.radius_8dp)),
        modifier = modifier
    ) {
        SearchBarItem(
            keyword = keyword,
            onClickSearchBtn = { onEvent(UiEvent.SearchBook(keyword)) }
        )

        LazyColumn(
            contentPadding = PaddingValues(bottom = dimensionResource(R.dimen.padding_12dp)),
            verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_4dp)),
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(
                key = { it.id },
                items = bookList
            ) { book ->
                BookItem(
                    book = book
                )
            }
        }
    }
}
