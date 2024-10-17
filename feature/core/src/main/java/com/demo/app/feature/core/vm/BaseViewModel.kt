package com.demo.app.feature.core.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.app.feature.core.state.FetchState
import com.demo.app.feature.core.state.RequestState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update


open class BaseViewModel : ViewModel() {

    private val _fetchState = MutableStateFlow<FetchState>(FetchState.Idle)
    val fetchState = _fetchState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1_000),
        initialValue = FetchState.Idle
    )

    private val _requestState = MutableStateFlow<RequestState>(RequestState.Idle)
    val requestState = _requestState.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1_000),
        initialValue = RequestState.Idle
    )

    fun updateFetchState(state: FetchState) {
        _fetchState.update { state }
    }

    fun updateRequestState(state: RequestState) {
        _requestState.update { state }
    }
}