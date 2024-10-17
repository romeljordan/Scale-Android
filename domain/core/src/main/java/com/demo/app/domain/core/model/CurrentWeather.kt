package com.demo.app.domain.core.model

data class CurrentWeather(
    val city: String,
    val country: String,
    val temp: Double,
    val type: String,
    val typeDescription: String,
    val icon: String,
    val sunrise: Long,
    val sunset: Long,
    val dateMillis: Long
) {
    fun toWeatherLog(): WeatherLog = WeatherLog(
        city = city,
        country = country,
        temp = temp,
        type = type,
        icon = icon,
        dateMillis = dateMillis
    )
}
