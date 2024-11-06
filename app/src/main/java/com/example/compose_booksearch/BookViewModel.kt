package com.example.compose_booksearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_booksearch.mapper.BookUiMapper
import com.example.compose_booksearch.uimodel.BookUiModel
import com.example.compose_booksearch.util.PAGE_SIZE
import com.example.domain.usecase.SearchBookUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface UiEvent {
    data class SearchBook(val keyword: String) : UiEvent
    data object Refresh : UiEvent
}

sealed interface LoadState {
    data object Loading : LoadState
    data object Success : LoadState
    data class Error(val error: Throwable) : LoadState
}

@HiltViewModel
class BookViewModel @Inject constructor(
    private val searchBookUseCase: SearchBookUseCase,
    private val bookUiMapper: BookUiMapper
): ViewModel() {
    private fun exceptionHandler(): CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            _loadState.update {
                LoadState.Error(throwable)
            }
        }

    private val _loadState: MutableStateFlow<LoadState> = MutableStateFlow(LoadState.Success)
    val loadState = _loadState.asStateFlow()

    private val _bookList: MutableStateFlow<List<BookUiModel>> = MutableStateFlow(listOf())
    val bookList = _bookList.asStateFlow()

    private val _totalCount: MutableStateFlow<Int> = MutableStateFlow(0)
    val totalCount = _totalCount.asStateFlow()

    private var currentPage = 1

    private fun searchBookByName(
        keyword: String,
        page: Int
    ) {
        _loadState.update {
            LoadState.Loading
        }
        viewModelLaunch(onSuccess = {
            val searchResult = bookUiMapper.mapToSearchResultUiModel(
                searchBookUseCase(
                    keyword = keyword,
                    page = currentPage,
                    pageSize = PAGE_SIZE
                )
            )

            _bookList.update {
                mutableListOf<BookUiModel>().apply {
                    addAll(searchResult.bookList)
                }
            }
            _totalCount.update {
                searchResult.totalCount
            }
        })
    }

    fun onEvent(event: UiEvent) {
        when (event) {
            is UiEvent.SearchBook -> {
                searchBookByName(
                    keyword = event.keyword,
                    page = currentPage
                )
            }
            is UiEvent.Refresh -> {}
        }
    }

    private fun viewModelLaunch(onSuccess: suspend () -> Unit) {
        viewModelScope.launch(
            context = exceptionHandler()
        ) {
            onSuccess.invoke()
            _loadState.update {
                LoadState.Success
            }
        }
    }
}