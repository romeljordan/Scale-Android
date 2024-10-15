package com.demo.app.feature.weather.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.demo.app.feature.weather.WeatherScreen

const val WEATHER_NAV_ROUTE = "weather_nav_route"

fun NavController.navigateToWeather() {
    navigate(route = WEATHER_NAV_ROUTE)
}

fun NavGraphBuilder.weatherScreen() {
    composable(
        route = WEATHER_NAV_ROUTE
    ) {
        WeatherScreen()
    }
}
