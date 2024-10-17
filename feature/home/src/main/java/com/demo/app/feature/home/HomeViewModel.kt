package com.demo.app.feature.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.demo.app.data.core.Session
import com.demo.app.domain.core.usecase.AuthUseCase
import com.demo.app.domain.core.usecase.OpenWeatherUseCase
import com.demo.app.feature.core.state.FetchState
import com.demo.app.feature.core.vm.BaseViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherUseCase: OpenWeatherUseCase,
    private val authUseCase: AuthUseCase,
    private val fusedLocationProviderClient: FusedLocationProviderClient
): BaseViewModel() {

    fun fetchLocationAndLogWeather() {
        fusedLocationProviderClient.lastLocation // don't need to check permission -> already sure that has permission from this point
            .addOnSuccessListener { location ->
                fetchAndLogWeather(location.latitude, location.longitude)
            }
    }

    private fun fetchAndLogWeather(latitude: Double, longitude: Double) = viewModelScope.launch {
        Session.current?.let { session ->
            updateFetchState(FetchState.Loading)
            weatherUseCase.fetchOpenWeather(latitude, longitude).onSuccess { cWeather ->
                val jsonString = Gson().toJson(cWeather.toWeatherLog())
                authUseCase.log(session.userId, jsonString).onSuccess {
                    updateFetchState(FetchState.Idle)
                }.onFailure {
                    updateFetchState(FetchState.Error(it.message))
                }
            }.onFailure {
                Log.e("ScaleLog", "Fetch error: $it")
                updateFetchState(FetchState.Error(it.message))
            }
        }
    }
}
