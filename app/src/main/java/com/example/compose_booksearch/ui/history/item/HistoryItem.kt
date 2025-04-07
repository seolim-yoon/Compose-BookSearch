package com.example.compose_booksearch.ui.history.item

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_booksearch.R
import com.example.compose_booksearch.ui.theme.ComposeBookSearchTheme
import com.example.compose_booksearch.uimodel.HistoryUiModel

@Composable
internal fun HistoryItem(
    history: HistoryUiModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_8dp))
    ) {
        Text(
            text = history.bookTitle,
            modifier = Modifier.weight(1f)
                .padding(end = dimensionResource(R.dimen.padding_8dp))
        )
        Icon(
            imageVector = Icons.Filled.Clear,
            contentDescription = null
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewHistoryItem() {
    ComposeBookSearchTheme {
        HistoryItem(
            history = HistoryUiModel(
                id = 0,
                bookTitle = "히스토리"
            )
        )
    }
}