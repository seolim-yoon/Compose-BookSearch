package com.example.compose_booksearch.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp


@Immutable
data class BookTypography(
    val title18: TextStyle = TextStyle.Default,
    val title16: TextStyle = TextStyle.Default,
    val body16: TextStyle = TextStyle.Default,
    val body14: TextStyle = TextStyle.Default
)

val Typography = BookTypography(
    title18 = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 24.sp
    ),
    title16 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 22.sp
    ),
    body16 = TextStyle(
        fontSize = 16.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 22.sp
    ),
    body14 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp
    )
)

val LocalBookTypography = staticCompositionLocalOf {
    BookTypography()
}