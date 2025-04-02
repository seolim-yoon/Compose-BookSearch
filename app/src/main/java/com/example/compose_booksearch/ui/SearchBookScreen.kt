package com.example.compose_booksearch.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.compose_booksearch.BookViewModel
import com.example.compose_booksearch.LoadState
import com.example.compose_booksearch.R
import com.example.compose_booksearch.ui.event.UiEvent
import com.example.compose_booksearch.uimodel.BookUiModel

@Composable
internal fun SearchBookScreen(
    onClickBookItem:(BookUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val viewModel: BookViewModel = hiltViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    var inputKeyword by remember { mutableStateOf("") }

    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_8dp)),
        modifier = modifier
    ) {
        SearchBarItem(
            inputText = inputKeyword,
            onValueChange = { inputKeyword = it },
            onClickSearchBtn = { keyword -> viewModel.onEvent(UiEvent.SearchBook(keyword)) },
            onClickClearBtn = { inputKeyword = "" }
        )

        if (state.totalCount > 0) {
            TotalCountItem(
                totalCount = state.totalCount
            )
        }

        BookList(
            loadState = state.loadState,
            bookList = state.bookList,
            onClickBookItem = onClickBookItem,
            loadMoreItem = { viewModel.onEvent(UiEvent.LoadMore) },
            onRefresh = { viewModel.onEvent(UiEvent.Refresh) }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun BookList(
    loadState: LoadState,
    bookList: List<BookUiModel>,
    onClickBookItem: (BookUiModel) -> Unit,
    loadMoreItem: () -> Unit,
    onRefresh: () -> Unit
) {
    val listState = rememberLazyListState()
    val needLoadMore by remember {
        derivedStateOf {
            val totalItemsCount = listState.layoutInfo.totalItemsCount
            val lastVisibleItemIndex = listState.layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0
            lastVisibleItemIndex != 0 && lastVisibleItemIndex >= totalItemsCount - 1
        }
    }

    LaunchedEffect(needLoadMore) {
        if (needLoadMore) {
            loadMoreItem()
        }
    }

    when (loadState) {
        is LoadState.Loading -> {
            LinearProgressIndicator(
                modifier = Modifier.fillMaxWidth()
            )
        }
        is LoadState.Success -> {
            CompositionLocalProvider(LocalOverscrollConfiguration provides null)  {
                LazyColumn(
                    state = listState,
                    contentPadding = PaddingValues(dimensionResource(R.dimen.padding_16dp)),
                    verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_8dp)),
                    modifier = Modifier.fillMaxSize()
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
        is LoadState.Error -> {
            ErrorScreen(
                errorMessage = loadState.error.message.toString(),
                onRefresh = onRefresh
            )
        }
    }
}
