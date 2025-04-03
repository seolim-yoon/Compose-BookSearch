package com.example.compose_booksearch.ui.item

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
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
import com.example.compose_booksearch.util.discountPercent


@Composable
internal fun BookItem(
    book: BookUiModel,
    onClickBookItem: (BookUiModel) -> Unit,
    onClickFavorite: (BookUiModel) -> Unit
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
            modifier = Modifier
                .background(
                    color = BookProgramAppTheme.colors.beige
                )
                .padding(dimensionResource(R.dimen.padding_12dp))
        ) {
            Box(
                contentAlignment = Alignment.BottomStart
            ) {
                AsyncImageItem(
                    imgUrl = book.thumbnail,
                    modifier = Modifier
                        .fillMaxWidth(0.25f)
                        .aspectRatio(3f / 4f)
                        .padding(dimensionResource(R.dimen.padding_4dp))
                )

                Icon(
                    imageVector = if (book.isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            onClickFavorite(book)
                        }
                        .padding(dimensionResource(R.dimen.padding_4dp))
                )
            }


            Column(
                verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_4dp)),
                modifier = Modifier.fillMaxWidth()
            ) {

                BookTitleInfo(title = book.title, authors = book.authors)
                BookPriceInfo(price = book.price, salePrice = book.salePrice)
                Text(
                    text = book.contents,
                    style = BookProgramAppTheme.typography.body14,
                    color = BookProgramAppTheme.colors.gray50,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Composable
internal fun BookTitleInfo(
    title: String,
    authors: String
) {
    Text(
        text = title,
        style = BookProgramAppTheme.typography.title18,
        color = BookProgramAppTheme.colors.gray90,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )

    Text(
        text = authors,
        style = BookProgramAppTheme.typography.title16,
        color = BookProgramAppTheme.colors.gray90,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
internal fun BookPriceInfo(
    price: Int,
    salePrice: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(
            space = dimensionResource(R.dimen.padding_4dp)
        )
    ) {
        val isSale = salePrice != price

        Text(
            text = stringResource(R.string.price_won, price),
            style = if (isSale) BookProgramAppTheme.typography.strikeBody14
                  else BookProgramAppTheme.typography.body14,
            color = BookProgramAppTheme.colors.gray90,
        )
        if (isSale) {
            Text(
                text = stringResource(R.string.price_won, salePrice),
                style = BookProgramAppTheme.typography.body14,
                color = BookProgramAppTheme.colors.gray90,
            )
            Text(
                text = stringResource(
                    R.string.discount_percent,
                    discountPercent(
                        price = price.toDouble(),
                        salePrice = salePrice.toDouble()
                    )
                ),
                style = BookProgramAppTheme.typography.body16,
                color = BookProgramAppTheme.colors.red,
                modifier = Modifier.padding(start = dimensionResource(R.dimen.padding_4dp))
            )
        }
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
                authors = "한강",
                publisher = "창비",
                price = 15000,
                salePrice = 13500,
                thumbnail = "https://contents.kyobobook.co.kr/sih/fit-in/458x0/pdt/9788936434120.jpg",
                status = "판매중",
                isFavorite = false
            ),
            onClickBookItem = {},
            onClickFavorite = {}
        )
    }
}