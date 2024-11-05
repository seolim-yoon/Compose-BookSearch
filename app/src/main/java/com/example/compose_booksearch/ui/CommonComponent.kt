package com.example.compose_booksearch.ui

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import coil.compose.AsyncImage

@Composable
internal fun AsyncImageItem(
    imgUrl: String,
    modifier: Modifier = Modifier
) {
    if (LocalInspectionMode.current) {
        Icon(
            imageVector = Icons.Default.Info,
            contentDescription = null,
            modifier = modifier
        )
    } else {
        AsyncImage(
            model = imgUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }
}