package com.example.compose_booksearch.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.compose_booksearch.ui.effect.Effect
import com.example.compose_booksearch.ui.event.UiEvent
import com.example.compose_booksearch.ui.state.UiState
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<State: UiState> : ViewModel() {
    private val initialState : State by lazy { createInitialState() }
    abstract fun createInitialState() : State

    private val currentState: State
        get() = uiState.value

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _effect: Channel<Effect> = Channel()
    val effect = _effect.receiveAsFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        handleException(exception)
    }

    abstract fun handleException(throwable: Throwable)

    protected fun setState(reduce: State.() -> State) {
        _uiState.update {
            currentState.reduce()
        }
    }

    protected fun setEffect(builder: () -> Effect) {
        val effectValue = builder()
        viewModelScope.launch { _effect.send(effectValue) }
    }

    abstract fun onEvent(event: UiEvent)

    protected fun viewModelLaunch(
        onSuccess: suspend () -> Unit
    ) {
        viewModelScope.launch(
            context = exceptionHandler
        ) {
            onSuccess.invoke()
        }
    }
}