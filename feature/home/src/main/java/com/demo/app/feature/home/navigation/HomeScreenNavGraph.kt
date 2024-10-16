package com.demo.app.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.demo.app.feature.weather.navigation.WeatherNavResult
import com.demo.app.feature.weather.navigation.weatherScreen
import com.demoapp.feature.records.navigation.recordsScreen

@Composable
internal fun HomeScreenNavGraph(
    navController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        weatherScreen(
            onNavResult = { res ->
                when (res) {
                    WeatherNavResult.MoveBack -> navController.popBackStack()
                }
            }
        )

        recordsScreen()
    }
}
