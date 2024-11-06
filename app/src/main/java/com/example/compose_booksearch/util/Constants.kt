package com.example.compose_booksearch.util

const val PAGE_SIZE = 10

fun discountPercent(price: Double, salePrice: Double) = (((price - salePrice) / price) * 100).toInt()