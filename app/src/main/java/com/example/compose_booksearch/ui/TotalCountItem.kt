package com.example.compose_booksearch.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_booksearch.R
import com.example.compose_booksearch.ui.theme.BookProgramAppTheme
import com.example.compose_booksearch.ui.theme.ComposeBookSearchTheme

@Composable
internal fun TotalCountItem(
    totalCount: Int
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_4dp)),
        modifier = Modifier.fillMaxWidth()
            .padding(
                horizontal = dimensionResource(R.dimen.padding_12dp),
                vertical = dimensionResource(R.dimen.padding_8dp)
            )
    ) {
        Icon(
            imageVector = Icons.Filled.Create,
            contentDescription = null
        )
        Text(
            text = stringResource(R.string.total_count, totalCount),
            style = BookProgramAppTheme.typography.title16
        )
    }

}

@Preview(showBackground = true)
@Composable
private fun PreviewTotalCountItem() {
    ComposeBookSearchTheme {
        TotalCountItem(
           totalCount = 10000
        )
    }
}