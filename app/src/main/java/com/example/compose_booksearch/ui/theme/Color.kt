package com.example.compose_booksearch.ui.theme

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val Gray90 = Color(0xFF121212)
val Gray80 = Color(0xFF212126)
val Gray50 = Color(0xFF6B6B72)
val Gray40 = Color(0xFF84848B)
val Gray30 = Color(0xFF9E9EA3)
val Pink50 = Color(0xFFF94987)
val Blue = Color(0xFF49BEF9)
val White = Color(0xFFFFFFFF)

class BookColorScheme(
    gray90: Color = Gray90,
    gray80: Color = Gray80,
    gray50: Color = Gray50,
    gray40: Color = Gray40,
    gray30: Color = Gray30,
    pink50: Color = Pink50,
    blue: Color = Blue,
    white: Color = White
) {
    var gray90 by mutableStateOf(gray90)
        private set

    var gray80 by mutableStateOf(gray80)
        private set

    var gray50 by mutableStateOf(gray50)
        private set

    var gray40 by mutableStateOf(gray40)
        private set

    var gray30 by mutableStateOf(gray30)
        private set

    var pink50 by mutableStateOf(pink50)
        private set

    var blue by mutableStateOf(blue)
        private set

    var white by mutableStateOf(white)
        private set

    fun copy(
        gray90: Color = this.gray90,
        gray80: Color = this.gray80,
        gray50: Color = this.gray50,
        gray40: Color = this.gray40,
        gray30: Color = this.gray30,
        pink50: Color = this.pink50,
        blue: Color = this.blue,
        white: Color = this.white
    ) = BookColorScheme(
        gray90 = gray90,
        gray80 = gray80,
        gray50 = gray50,
        gray40 = gray40,
        gray30 = gray30,
        pink50 = pink50,
        blue = blue,
        white = white
    )

    fun updateColorsFrom(other: BookColorScheme) {
        gray90 = other.gray90
        gray80 = other.gray80
        gray50 = other.gray50
        gray40 = other.gray40
        gray30 = other.gray30
        pink50 = other.pink50
        blue = other.blue
        white = other.white
    }
}

val LocalBookColorScheme = staticCompositionLocalOf {
    BookColorScheme()
}