package com.demo.app.feature.weather

import androidx.lifecycle.viewModelScope
import com.demo.app.data.core.Session
import com.demo.app.domain.core.model.CurrentWeather
import com.demo.app.domain.core.usecase.AuthUseCase
import com.demo.app.domain.core.usecase.OpenWeatherUseCase
import com.demo.app.feature.core.state.FetchState
import com.demo.app.feature.core.state.RequestAction
import com.demo.app.feature.core.state.RequestState
import com.demo.app.feature.core.vm.BaseViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed interface WeatherRequestAction: RequestAction {
    data object LogOut: WeatherRequestAction
}

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val useCase: OpenWeatherUseCase,
    private val authUseCase: AuthUseCase,
    private val fusedLocationProviderClient: FusedLocationProviderClient
): BaseViewModel() {

    private val _currentWeather = MutableStateFlow<CurrentWeather?>(null)
    val currentWeather = _currentWeather.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1_000),
        initialValue = null
    )

    private val _fetchedDate = MutableStateFlow(System.currentTimeMillis())
    val fetchedDate = _fetchedDate.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(1_000),
        initialValue = System.currentTimeMillis()
    )

    init {
        requestLocation()
    }

    private fun fetchCurrentWeather(latitude: Double, longitude: Double) = viewModelScope.launch {
        updateFetchState(FetchState.Loading)
        useCase.fetchOpenWeather(latitude, longitude).onSuccess {
            _currentWeather.emit(it)
            _fetchedDate.update { System.currentTimeMillis() }
            updateFetchState(FetchState.Idle)
        }.onFailure {
            updateFetchState(FetchState.Error(it.message))
        }
    }

    fun requestLocation() = viewModelScope.launch {
        updateFetchState(FetchState.Loading)
        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener {
                fetchCurrentWeather(it.latitude, it.longitude)
            }
    }

    fun logOut()= viewModelScope.launch {
        Session.current?.let { session ->
            updateRequestState(RequestState.Loading)
            authUseCase.logout(session.userId).onSuccess {
                updateRequestState(RequestState.Done(WeatherRequestAction.LogOut))
            }.onFailure {
                updateRequestState(RequestState.Error(it.message))
            }
        }
    }
}
