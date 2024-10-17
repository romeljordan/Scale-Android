package com.demo.app.feature.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.demo.app.data.core.Session
import com.demo.app.domain.core.usecase.AuthUseCase
import com.demo.app.domain.core.usecase.OpenWeatherUseCase
import com.demo.app.feature.core.vm.BaseViewModel
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val weatherUseCase: OpenWeatherUseCase,
    private val authUseCase: AuthUseCase
): BaseViewModel() {

    fun fetchAndLogWeather() = viewModelScope.launch {
        Session.current?.let { session ->
            weatherUseCase.fetchOpenWeather(14.5995, 120.9842).onSuccess { cWeather ->
                val jsonString = Gson().toJson(cWeather.toWeatherLog())
                authUseCase.log(session.userId, jsonString)
            }.onFailure {
                Log.e("ScaleLog", "Fetch error: $it")
            }
        }
    }
}
