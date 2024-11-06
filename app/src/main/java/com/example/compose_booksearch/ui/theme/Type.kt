package com.example.compose_booksearch.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.sp


@Immutable
data class BookTypography(
    val title24: TextStyle = TextStyle.Default,
    val title20: TextStyle = TextStyle.Default,
    val title18: TextStyle = TextStyle.Default,
    val title16: TextStyle = TextStyle.Default,
    val body18: TextStyle = TextStyle.Default,
    val body16: TextStyle = TextStyle.Default,
    val body14: TextStyle = TextStyle.Default,
    val strikeBody14: TextStyle = TextStyle.Default
)

val Typography = BookTypography(
    title24 = TextStyle(
        fontSize = 24.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 30.sp
    ),
    title20 = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        lineHeight = 26.sp
    ),
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
    body18 = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 24.sp
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
    ),
    strikeBody14 = TextStyle(
        fontSize = 14.sp,
        fontWeight = FontWeight.Normal,
        lineHeight = 20.sp,
        textDecoration = TextDecoration.LineThrough
    )
)

val LocalBookTypography = staticCompositionLocalOf {
    BookTypography()
}