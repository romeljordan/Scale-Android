package com.demo.app.data.core.dto

import com.demo.app.domain.core.model.CurrentWeather

data class CurrentWeatherDto(
    val coord: CoordinatedDto,
    val weather: List<WeatherDto>,
    val main: MainDto,
    val sys: SysDto,
    val name: String
) {
    fun toDomainModel(): CurrentWeather = CurrentWeather(
        city = name,
        country = sys.country,
        temp = main.temp,
        type = weather.firstOrNull()?.main ?: "",
        typeDescription = weather.firstOrNull()?.description ?: "",
        icon = weather.firstOrNull()?.icon ?: "",
        sunset = sys.sunset,
        sunrise = sys.sunrise,
        dateMillis = System.currentTimeMillis()
    )
}

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
