package com.demo.app.data.core.api

import com.demo.app.data.core.dto.CurrentWeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface OpenWeatherApi {
    @GET("weather")
    suspend fun currentWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("appid") apiKey: String,
        @Query("units") unit: String
    ): Response<CurrentWeatherDto>
}
