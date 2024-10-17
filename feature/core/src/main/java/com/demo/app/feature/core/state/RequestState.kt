package com.demo.app.feature.core.state

interface RequestAction

sealed interface RequestState {
    data object Idle: RequestState
    data object Loading: RequestState
    data class Done(val action: RequestAction): RequestState
    data class Error(val error: String?): RequestState // TODO: add state
}
