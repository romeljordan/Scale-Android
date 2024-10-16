package com.demo.app.domain.core.usecase

import com.demo.app.domain.core.model.CurrentWeather

interface OpenWeatherUseCase {
    suspend fun fetchOpenWeather(
        latitude: Double,
        longitude: Double
    ): Result<CurrentWeather>
}
