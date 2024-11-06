package com.example.compose_booksearch.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose_booksearch.BookViewModel
import com.example.compose_booksearch.LoadState
import com.example.compose_booksearch.R
import com.example.compose_booksearch.UiEvent
import com.example.compose_booksearch.ui.theme.BookProgramAppTheme
import com.example.compose_booksearch.ui.theme.ComposeBookSearchTheme
import com.example.compose_booksearch.uimodel.BookUiModel


@Composable
internal fun MainScreen(
    onClickBookItem:(BookUiModel) -> Unit,
) {
    val viewModel: BookViewModel = hiltViewModel()
    val onEvent = viewModel::onEvent

    val bookList by viewModel.bookList.collectAsState()
    val totalCount by viewModel.totalCount.collectAsState()
    val loadState by viewModel.loadState.collectAsState()

    when (loadState) {
        is LoadState.Loading -> {
            LinearProgressIndicator()
        }
        is LoadState.Success -> {
            SearchBookScreen(
                totalCount = totalCount,
                bookList = bookList,
                onClickBookItem = onClickBookItem,
                onClickSearchBtn = { keyword -> onEvent(UiEvent.SearchBook(keyword)) }
            )
        }

        is LoadState.Error -> {
            ErrorScreen(
                errorMessage = (loadState as LoadState.Error).error.message.toString(),
                onRefresh = { onEvent(UiEvent.Refresh) }
            )
        }
    }
}

@Composable
internal fun ErrorScreen(
    errorMessage: String,
    onRefresh: () -> Unit
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        Icon(
            imageVector = Icons.Default.Warning,
            contentDescription = null,
            modifier = Modifier.size(100.dp)
        )

        Text(
            text = stringResource(R.string.refresh),
            color = BookProgramAppTheme.colors.white,
            modifier = Modifier
                .background(
                    shape = RoundedCornerShape(dimensionResource(R.dimen.radius_12dp)),
                    color = BookProgramAppTheme.colors.blue
                )
                .clickable {
                    onRefresh()
                }
                .padding(dimensionResource(R.dimen.padding_12dp))
        )

        Text(
            text = errorMessage,
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_16dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewErrorScreen() {
    ComposeBookSearchTheme {
        ErrorScreen(
            errorMessage = "에러 발생!",
            onRefresh = {}
        )
    }
}