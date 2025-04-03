package com.example.compose_booksearch.util

const val PAGE_SIZE = 10
const val BOOK_ITEM_TYPE = "BookItemType"

fun discountPercent(price: Double, salePrice: Double) = (((price - salePrice) / price) * 100).toInt()