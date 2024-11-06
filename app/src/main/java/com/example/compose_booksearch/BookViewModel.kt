package com.example.compose_booksearch

import com.airbnb.mvrx.MavericksState
import com.airbnb.mvrx.MavericksViewModel
import com.airbnb.mvrx.MavericksViewModelFactory
import com.airbnb.mvrx.hilt.AssistedViewModelFactory
import com.airbnb.mvrx.hilt.hiltMavericksViewModelFactory
import com.example.compose_booksearch.mapper.BookUiMapper
import com.example.compose_booksearch.uimodel.BookUiModel
import com.example.compose_booksearch.util.PAGE_SIZE
import com.example.domain.usecase.SearchBookUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

sealed interface UiEvent {
    data class SearchBook(val keyword: String) : UiEvent
    data object Refresh : UiEvent
    data object LoadMore : UiEvent
}

sealed interface LoadState {
    data object Loading : LoadState
    data object Success : LoadState
    data class Error(val error: Throwable) : LoadState
}

data class BookUiState(
    val loadState: LoadState = LoadState.Success,
    val totalCount: Int = 0,
    val bookList: List<BookUiModel> = emptyList()
) : MavericksState

class BookViewModel @AssistedInject constructor(
    @Assisted initialState: BookUiState,
    private val searchBookUseCase: SearchBookUseCase,
    private val bookUiMapper: BookUiMapper
) : MavericksViewModel<BookUiState>(initialState) {

    private fun exceptionHandler(): CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
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
                withState { state ->
                    setState {
                        copy(
                            loadState = LoadState.Success,
                            bookList = state.bookList.toMutableList().apply {
                                addAll(moreBookList)
                            }
                        )
                    }
                }
            }
        })
    }

    fun onEvent(event: UiEvent) {
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
        }
    }

    private fun viewModelLaunch(onSuccess: suspend () -> Unit) {
        viewModelScope.launch(
            context = exceptionHandler()
        ) {
            onSuccess.invoke()
        }
    }

    @AssistedFactory
    interface Factory : AssistedViewModelFactory<BookViewModel, BookUiState> {
        override fun create(state: BookUiState): BookViewModel
    }

    companion object : MavericksViewModelFactory<BookViewModel, BookUiState> by hiltMavericksViewModelFactory()
}