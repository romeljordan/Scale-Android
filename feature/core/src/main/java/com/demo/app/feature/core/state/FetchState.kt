package com.demo.app.feature.core.state

sealed interface FetchState {
    data object Idle: FetchState
    data object Loading: FetchState
    data class Error(val error: String?): FetchState // TODO: add state
}
