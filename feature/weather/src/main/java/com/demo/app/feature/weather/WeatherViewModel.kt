package com.demo.app.feature.weather

import androidx.lifecycle.viewModelScope
import com.demo.app.domain.core.model.CurrentWeather
import com.demo.app.domain.core.usecase.OpenWeatherUseCase
import com.demo.app.feature.core.state.FetchState
import com.demo.app.feature.core.state.RequestAction
import com.demo.app.feature.core.vm.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface WeatherRequestAction: RequestAction {
    data object LogCurrentWeather: WeatherRequestAction
}

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val useCase: OpenWeatherUseCase
): BaseViewModel() {

    private val _currentWeather = MutableStateFlow<CurrentWeather?>(null)
    val currentWeather = _currentWeather.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1_000),
        initialValue = null
    )

    init {
        fetchCurrentWeather()
    }

    fun fetchCurrentWeather() = viewModelScope.launch {
        updateFetchState(FetchState.Loading)
        useCase.fetchOpenWeather(14.5995, 120.9842).onSuccess {
            _currentWeather.emit(it)
            updateFetchState(FetchState.Idle)
        }.onFailure {
            updateFetchState(FetchState.Error(it.message))
        }
    }
}
