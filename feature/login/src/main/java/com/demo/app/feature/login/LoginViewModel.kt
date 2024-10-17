package com.demo.app.feature.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.demo.app.domain.core.usecase.OpenWeatherUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
     private val useCase: OpenWeatherUseCase
): ViewModel() {

    init {
        viewModelScope.launch {
            val result = useCase.fetchOpenWeather(14.5995, 120.984222)
            Log.i("QWERTY", "fetch result: $result")
        }
    }
}
