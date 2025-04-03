package com.example.compose_booksearch.ui.screen

import android.widget.Toast
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import com.example.compose_booksearch.BookUiState
import com.example.compose_booksearch.LoadState
import com.example.compose_booksearch.R
import com.example.compose_booksearch.ui.effect.Effect
import com.example.compose_booksearch.ui.event.UiEvent
import com.example.compose_booksearch.ui.item.BookItem
import com.example.compose_booksearch.ui.item.SearchBarItem
import com.example.compose_booksearch.ui.item.TotalCountItem
import com.example.compose_booksearch.uimodel.BookUiModel
import com.example.compose_booksearch.util.BOOK_ITEM_TYPE
import kotlinx.coroutines.flow.Flow

@Composable
internal fun SearchBookScreen(
    state: BookUiState,
    onEvent: (UiEvent) -> Unit,
    effectFlow: Flow<Effect>,
    onClickBookItem:(BookUiModel) -> Unit,
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    var inputKeyword by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        effectFlow.collect { effect ->
            when(effect) {
                is Effect.Toast -> Toast.makeText(context, effect.msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_8dp)),
        modifier = modifier
    ) {
        SearchBarItem(
            inputText = inputKeyword,
            onValueChange = { inputKeyword = it },
            onClickSearchBtn = { keyword -> onEvent(UiEvent.SearchBook(keyword)) },
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
            onClickFavorite = { onEvent(UiEvent.ClickFavorite(it.id)) },
            loadMoreItem = { onEvent(UiEvent.LoadMore) },
            onRefresh = { onEvent(UiEvent.Refresh) }
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun BookList(
    loadState: LoadState,
    bookList: List<BookUiModel>,
    onClickBookItem: (BookUiModel) -> Unit,
    onClickFavorite: (BookUiModel) -> Unit,
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
                        contentType = { BOOK_ITEM_TYPE },
                        items = bookList
                    ) { book ->
                        BookItem(
                            book = book,
                            onClickBookItem = onClickBookItem,
                            onClickFavorite = onClickFavorite
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
