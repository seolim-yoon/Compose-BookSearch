package com.example.compose_booksearch.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_booksearch.R
import com.example.compose_booksearch.ui.theme.BookProgramAppTheme
import com.example.compose_booksearch.ui.theme.ComposeBookSearchTheme
import com.example.compose_booksearch.uimodel.BookUiModel


@Composable
internal fun BookItem(
    book: BookUiModel,
    onClickBookItem:(BookUiModel) -> Unit
) {
    Card(
        shape = RoundedCornerShape(dimensionResource(R.dimen.radius_12dp)),
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClickBookItem(book)
            }
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_12dp)),
            modifier = Modifier.padding(dimensionResource(R.dimen.padding_12dp))
        ) {
            AsyncImageItem(
                imgUrl = book.thumbnail,
                modifier = Modifier
                    .fillMaxWidth(0.25f)
                    .padding(dimensionResource(R.dimen.padding_4dp))
            )

            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_4dp)),
                modifier = Modifier.fillMaxWidth()
            ) {

                BookTitleInfo(book = book)
                BookPriceInfo(book = book)
                Text(
                    text = book.contents,
                    style = BookProgramAppTheme.typography.body14,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
internal fun BookTitleInfo(
    book: BookUiModel
) {
    Text(
        text = book.title,
        style = BookProgramAppTheme.typography.title18,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )

    Text(
        text = book.authors.toString(),
        style = BookProgramAppTheme.typography.title16,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
internal fun BookPriceInfo(
    book: BookUiModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = dimensionResource(R.dimen.padding_4dp)
        )
    ) {
        Text(
            text = stringResource(R.string.price_won, book.price),
            style = BookProgramAppTheme.typography.body14,
        )
        Text(
            text = stringResource(R.string.price_won, book.salePrice),
            style = BookProgramAppTheme.typography.body14
        )
        Text(
            text = "30%",
            style = BookProgramAppTheme.typography.body14
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewBookItem() {
    ComposeBookSearchTheme {
        BookItem(
            book = BookUiModel(
                id = 0,
                title = "소년이 온다",
                contents = "2014년 만해문학상, 2017년 이탈리아 말라파르테 문학상을 수상하고 전세계 20여개국에 번역 출간되며 세계를 사로잡은 우리 시대의 소설 『소년이 온다』.",
                url = "https://product.kyobobook.co.kr/detail/S000000610612",
                authors = listOf("한강"),
                publisher = "창비",
                price = 15000,
                salePrice = 13500,
                thumbnail = "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788936434120.jpg",
                status = "판매중"
            ),
            onClickBookItem = {}
        )
    }
}