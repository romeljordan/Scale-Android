package com.demoapp.feature.records

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.demo.app.data.core.Session
import com.demo.app.domain.core.model.WeatherLog
import com.demo.app.domain.core.usecase.AuthUseCase
import com.demo.app.feature.core.state.FetchState
import com.demo.app.feature.core.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordsViewModel @Inject constructor(
    private val useCase: AuthUseCase
): BaseViewModel() {
    private val _logs = MutableStateFlow<List<WeatherLog>>(emptyList())
    val logs = _logs.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1_000),
        initialValue = emptyList()
    )

    init {
        fetchWeatherLogs()
    }

    fun fetchWeatherLogs() = viewModelScope.launch {
        Session.current?.let { session ->
            updateFetchState(FetchState.Loading)
            useCase.logs(session.userId).onSuccess {
                _logs.emit(it)
                updateFetchState(FetchState.Idle)
            }.onFailure {
                Log.i("checker", "failed records result: $it")
                updateFetchState(FetchState.Error(it.message))
            }
        }
    }
}
