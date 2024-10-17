package com.demo.app.domain.core.model

data class WeatherLog(
    val city: String,
    val country: String,
    val temp: Double,
    val type: String,
    val icon: String,
    val dateMillis: Long
)
