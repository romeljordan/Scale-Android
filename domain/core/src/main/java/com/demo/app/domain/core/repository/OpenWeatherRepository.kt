package com.demo.app.domain.core.repository

import com.demo.app.domain.core.model.CurrentWeather

interface OpenWeatherRepository {
    suspend fun fetchCurrentWeather(
        latitude: Double,
        longitude: Double
    ): CurrentWeather
}
