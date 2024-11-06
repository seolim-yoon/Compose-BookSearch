package com.example.compose_booksearch.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.remember

private val DarkColorScheme = darkColorScheme(
    primary = OliveGreen,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = SageGreen
)

private val LightColorScheme = lightColorScheme(
    primary = OliveGreen,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = SageGreen
)

@Composable
fun ComposeBookSearchTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    colors: BookColorScheme = BookProgramAppTheme.colors,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val currentColor = remember { colors }
    val rememberedColors = remember { currentColor.copy() }.apply { updateColorsFrom(currentColor) }

    CompositionLocalProvider(
        LocalBookTypography provides Typography,
        LocalBookColorScheme provides rememberedColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            content = content
        )
    }
}

object BookProgramAppTheme {
    val typography: BookTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalBookTypography.current

    val colors: BookColorScheme
        @Composable
        @ReadOnlyComposable
        get() = LocalBookColorScheme.current
}