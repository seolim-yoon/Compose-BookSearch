package com.example.compose_booksearch.ui.book.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_booksearch.R
import com.example.compose_booksearch.ui.book.contract.BookUiEvent
import com.example.compose_booksearch.ui.book.item.AsyncImageItem
import com.example.compose_booksearch.ui.theme.BookProgramAppTheme
import com.example.compose_booksearch.ui.theme.ComposeBookSearchTheme
import com.example.compose_booksearch.uimodel.BookUiModel

@Composable
internal fun DetailScreen(
    book: BookUiModel,
    onEvent: (BookUiEvent) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_8dp)),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(dimensionResource(R.dimen.padding_16dp))
    ) {

        AsyncImageItem(
            imgUrl = book.thumbnail,
            modifier = Modifier.aspectRatio(3f / 4f)
                .padding(dimensionResource(R.dimen.padding_24dp))
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = book.title,
                style = BookProgramAppTheme.typography.title24,
                color = BookProgramAppTheme.colors.gray90,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Icon(
                imageVector = if (book.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = null,
                modifier = Modifier
                    .clickable {
                        onEvent(BookUiEvent.ClickFavorite(book.id))
                    }
                    .padding(dimensionResource(R.dimen.padding_4dp))
            )
        }

        Text(
            text = book.authors,
            style = BookProgramAppTheme.typography.title20,
            color = BookProgramAppTheme.colors.gray90,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = book.contents,
            style = BookProgramAppTheme.typography.body18,
            color = BookProgramAppTheme.colors.gray50
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewDetailScreen() {
   ComposeBookSearchTheme {
       DetailScreen(
           book = BookUiModel(
               id = 0,
               title = "소년이 온다",
               authors = "한강",
               contents = "2014년 만해문학상, 2017년 이탈리아 말라파르테 문학상을 수상하고 전세계 20여개국에 번역 출간되며 세계를 사로잡은 우리 시대의 소설 『소년이 온다』.",
               url = "",
               publisher = "",
               price = 0,
               salePrice = 0,
               thumbnail = "https://product.kyobobook.co.kr/detail/S000000610612",
               status = "",
               isFavorite = false
           ),
           onEvent = {

           }
       )
   }
}
