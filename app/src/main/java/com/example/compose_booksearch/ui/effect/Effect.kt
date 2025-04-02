package com.example.compose_booksearch.ui.effect

sealed interface Effect {
    data class Toast(val msg: String): Effect
}