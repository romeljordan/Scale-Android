package com.demo.app.domain.core.usecase

import com.demo.app.domain.core.model.CurrentWeather
import com.demo.app.domain.core.repository.OpenWeatherRepository
import javax.inject.Inject

class OpenWeatherUseCaseImpl @Inject constructor(
    private val repository: OpenWeatherRepository
): OpenWeatherUseCase {

    override suspend fun fetchOpenWeather(
        latitude: Double,
        longitude: Double
    ): Result<CurrentWeather> {
        return try {
            Result.success(repository.fetchCurrentWeather(latitude, longitude))
        } catch (error: Exception) {
            Result.failure(error)
        }

    }
}