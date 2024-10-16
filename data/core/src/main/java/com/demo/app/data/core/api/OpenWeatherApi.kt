package com.demo.app.data.core.api

import com.demo.app.data.core.dto.CurrentWeatherDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenWeatherApi {
    @GET("/weather?lat={latitude}&lon={longitude}&appid={api_key}")
    suspend fun currentWeather(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double,
        @Path("api_key") apiKey: String
    ): Response<CurrentWeatherDto>
}
