package com.demo.app.domain.core.model

data class CurrentWeather(
    val city: String,
    val country: String,
    val temperature: Double,
    val type: String,
    val typeDescription: String,
    val icon: String,
    val sunrise: Long,
    val sunset: Long,
    val dateMillis: Long
)
