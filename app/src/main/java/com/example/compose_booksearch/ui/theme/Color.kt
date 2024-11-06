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
val Gray50 = Color(0xFF6B6B72)
val White = Color(0xFFFFFFFF)
val Red = Color(0xFFFF0000)
val SageGreen = Color(0xFFD3E4CD)
val OliveGreen = Color(0xFFA3B18A)
val Beige = Color(0xFFF5F5DC)
val LightGray = Color(0xFFE0E0E0)

class BookColorScheme(
    gray90: Color = Gray90,
    gray50: Color = Gray50,
    red: Color = Red,
    white: Color = White,
    sageGreen: Color = SageGreen,
    oliveGreen: Color = OliveGreen,
    beige: Color = Beige,
    lightGray: Color = LightGray
) {
    var gray90 by mutableStateOf(gray90)
        private set

    var gray50 by mutableStateOf(gray50)
        private set

    var red by mutableStateOf(red)
        private set

    var white by mutableStateOf(white)
        private set

    var sageGreen by mutableStateOf(sageGreen)
        private set

    var oliveGreen by mutableStateOf(oliveGreen)
        private set

    var beige by mutableStateOf(beige)
        private set

    var lightGray by mutableStateOf(lightGray)
        private set

    fun copy(
        gray90: Color = this.gray90,
        gray50: Color = this.gray50,
        red: Color = this.red,
        white: Color = this.white,
        sageGreen: Color = this.sageGreen,
        oliveGreen: Color = this.oliveGreen,
        beige: Color = this.beige,
        lightGray: Color = this.lightGray
    ) = BookColorScheme(
        gray90 = gray90,
        gray50 = gray50,
        red = red,
        white = white,
        sageGreen = sageGreen,
        oliveGreen = oliveGreen,
        beige = beige,
        lightGray = lightGray,
    )

    fun updateColorsFrom(other: BookColorScheme) {
        gray90 = other.gray90
        gray50 = other.gray50
        red = other.red
        white = other.white
        sageGreen = other.sageGreen
        oliveGreen = other.oliveGreen
        beige = other.beige
        lightGray = other.lightGray
    }
}

val LocalBookColorScheme = staticCompositionLocalOf {
    BookColorScheme()
}