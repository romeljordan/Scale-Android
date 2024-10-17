package com.demo.app.data.core.repository

import com.demo.app.data.core.datasource.OpenWeatherRemoteDataSourceImpl
import com.demo.app.domain.core.model.CurrentWeather
import com.demo.app.domain.core.repository.OpenWeatherRepository
import javax.inject.Inject

class OpenWeatherRepositoryImpl @Inject constructor(
    private val dataSource: OpenWeatherRemoteDataSourceImpl
): OpenWeatherRepository {

    override suspend fun fetchCurrentWeather(latitude: Double, longitude: Double): CurrentWeather {
        val response = dataSource.fetchCurrentWeather(latitude, longitude)
        return if (response.isSuccessful) {
            response.body()?.toDomainModel() ?: throw Throwable("No body was found")
        } else { // TODO: change to Exception
            throw Throwable("Not successful call")
        }
    }
}
