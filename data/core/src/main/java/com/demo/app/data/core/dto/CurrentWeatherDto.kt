package com.demo.app.data.core.dto

data class CurrentWeatherDto(
    val coord: CoordinatedDto,
    val weather: List<WeatherDto>,
    val main: MainDto,
    val sys: SysDto,
    val name: String
)

data class CoordinatedDto(
    val lon: Double,
    val lat: Double
)

data class WeatherDto(
    val id: Int,
    val main: String,
    val description: String,
    val icon: String
)

data class MainDto(
    val temp: Double,
    val feelsLike: Double,
    val tempMin: Double,
    val tempMax: Double
)

data class SysDto(
    val type: Int,
    val id: Int,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)
