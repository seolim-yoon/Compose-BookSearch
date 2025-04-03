package com.example.compose_booksearch

import com.example.compose_booksearch.base.BaseViewModel
import com.example.compose_booksearch.mapper.BookUiMapper
import com.example.compose_booksearch.ui.event.UiEvent
import com.example.compose_booksearch.ui.state.UiState
import com.example.compose_booksearch.uimodel.BookUiModel
import com.example.compose_booksearch.util.PAGE_SIZE
import com.example.domain.usecase.SearchBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

sealed interface LoadState {
    data object Loading : LoadState
    data object Success : LoadState
    data class Error(val error: Throwable) : LoadState
}

data class BookUiState(
    val loadState: LoadState = LoadState.Success,
    val totalCount: Int = 0,
    val bookList: List<BookUiModel> = emptyList()
) : UiState

@HiltViewModel
class BookViewModel @Inject constructor(
    private val searchBookUseCase: SearchBookUseCase,
    private val bookUiMapper: BookUiMapper
) : BaseViewModel<BookUiState>() {

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
                searchBookUseCase(
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
                searchBookUseCase(
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

    override fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.SearchBook -> {
                searchBookByName(keyword = event.keyword)
            }

            is UiEvent.Refresh -> {
                searchBookByName(keyword = currentKeyword)
            }

            is UiEvent.LoadMore -> {
                loadMoreBookList(++currentPage)
            }

            is UiEvent.ClickFavorite -> {
                clickFavorite(event.bookId)
            }
        }
    }
}