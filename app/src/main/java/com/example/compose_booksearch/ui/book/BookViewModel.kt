package com.example.compose_booksearch.ui.book

import com.example.compose_booksearch.base.BaseViewModel
import com.example.compose_booksearch.base.LoadState
import com.example.compose_booksearch.mapper.BookUiMapper
import com.example.compose_booksearch.ui.book.contract.BookUiEffect
import com.example.compose_booksearch.ui.book.contract.BookUiEvent
import com.example.compose_booksearch.ui.book.contract.BookUiState
import com.example.compose_booksearch.util.PAGE_SIZE
import com.example.data.repository.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookViewModel @Inject constructor(
    private val bookRepository: BookRepository,
    private val bookUiMapper: BookUiMapper
) : BaseViewModel<BookUiState, BookUiEvent, BookUiEffect>() {

    override fun createInitialState(): BookUiState = BookUiState()

    override fun handleException(throwable: Throwable) {
        setState {
            copy(
                loadState = LoadState.Error(throwable)
            )
        }
    }

    private var currentPage = 1
    private var currentKeyword = ""

    private fun searchBookByName(
        keyword: String
    ) {
        setState {
            copy(
                loadState = LoadState.Loading
            )
        }
        viewModelLaunch(onSuccess = {
            currentPage = 1
            currentKeyword = keyword

            val searchResult = bookUiMapper.mapToSearchResultUiModel(
                bookRepository.searchBooksByName(
                    keyword = keyword,
                    page = currentPage,
                    pageSize = PAGE_SIZE
                )
            )

            setState {
                copy(
                    loadState = LoadState.Success,
                    bookList = searchResult.bookList,
                    totalCount = searchResult.totalCount
                )
            }
        })
    }

    private fun loadMoreBookList(page: Int) {
        viewModelLaunch(onSuccess = {
            val moreBookList = bookUiMapper.mapToSearchResultUiModel(
                bookRepository.searchBooksByName(
                    keyword = currentKeyword,
                    page = page,
                    pageSize = PAGE_SIZE
                )
            ).bookList

            if (moreBookList.isNotEmpty()) {
                setState {
                    copy(
                        loadState = LoadState.Success,
                        bookList = bookList.toMutableList().apply {
                            addAll(moreBookList)
                        }
                    )
                }
            }
        })
    }

    private fun clickFavorite(bookId: Int) {
        viewModelLaunch(
            onSuccess = {
                setState {
                    copy(
                        bookList = bookList.map { item ->
                            if (item.id == bookId) {
                                item.copy(
                                    isFavorite = !item.isFavorite
                                )
                            } else item
                        }
                    )
                }
            }
        )
    }

    override fun onEvent(event: BookUiEvent) {
        when (event) {
            is BookUiEvent.NavigateToDetail -> {
                setEffect {
                    BookUiEffect.NavigateToDetail(event.bookId)
                }
            }

            is BookUiEvent.SearchBook -> {
                searchBookByName(keyword = event.keyword)
            }

            is BookUiEvent.Refresh -> {
                searchBookByName(keyword = currentKeyword)
            }

            is BookUiEvent.LoadMore -> {
                loadMoreBookList(++currentPage)
            }

            is BookUiEvent.ClickFavorite -> {
                clickFavorite(event.bookId)
            }
        }
    }
}