package com.demo.app.data.core.datasource

import android.content.Context
import com.demo.app.data.core.R
import com.demo.app.data.core.api.OpenWeatherApi
import com.demo.app.data.core.dto.CurrentWeatherDto
import dagger.hilt.android.qualifiers.ApplicationContext
import retrofit2.Response
import javax.inject.Inject

class OpenWeatherRemoteDataSourceImpl @Inject constructor(
    private val api: OpenWeatherApi,
    @ApplicationContext private val context: Context
) {

    suspend fun fetchCurrentWeather(
        latitude: Double,
        longitude: Double
    ): Response<CurrentWeatherDto> {
        return api.currentWeather(
            latitude = latitude,
            longitude = longitude,
            apiKey = context.getString(R.string.open_weather_api_key)
        )
    }
}
