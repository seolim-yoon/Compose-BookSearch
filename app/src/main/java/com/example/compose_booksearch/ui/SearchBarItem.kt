package com.example.compose_booksearch.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import com.example.compose_booksearch.R
import com.example.compose_booksearch.ui.theme.BookProgramAppTheme
import com.example.compose_booksearch.ui.theme.ComposeBookSearchTheme

@Composable
internal fun SearchBarItem(
    inputText: String,
    onValueChange: (String) -> Unit,
    onClickSearchBtn: (String) -> Unit,
    onClickClearBtn: () -> Unit
) {
    val keyboardController = LocalSoftwareKeyboardController.current

    Row(
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_8dp)),
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(
            horizontal = dimensionResource(R.dimen.padding_12dp),
            vertical = dimensionResource(R.dimen.padding_8dp)
        )
    ) {
        OutlinedTextField(
            value = inputText,
            onValueChange = onValueChange,
            singleLine = true,
            placeholder = {
                Text(
                    text = stringResource(R.string.search_place_holder)
                )
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = null
                )
            },
            trailingIcon = {
                IconButton(
                    onClick = onClickClearBtn
                ) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = null
                    )
                }
            },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide()
                }
            ),
            modifier = Modifier.weight(1f)
        )

        Text(
            text = stringResource(R.string.search),
            style = BookProgramAppTheme.typography.body14,
            color = BookProgramAppTheme.colors.gray90,
            modifier = Modifier
                .background(
                    color = BookProgramAppTheme.colors.oliveGreen,
                    shape = RoundedCornerShape(dimensionResource(R.dimen.radius_12dp))
                )
                .clickable { onClickSearchBtn(inputText) }
                .padding(
                    vertical = dimensionResource(R.dimen.padding_16dp),
                    horizontal = dimensionResource(R.dimen.padding_24dp)
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchBarItem() {
    ComposeBookSearchTheme {
        SearchBarItem(
            inputText = "",
            onValueChange = { },
            onClickSearchBtn = { },
            onClickClearBtn = { }
        )
    }
}
