package com.demo.app.data.core.api

import com.demo.app.data.core.dto.CurrentWeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET("weather?lat={latitude}&lon={longitude}&appid={api_key}")
    suspend fun currentWeather(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("api_key") apiKey: String
    ): Response<CurrentWeatherDto>
}
