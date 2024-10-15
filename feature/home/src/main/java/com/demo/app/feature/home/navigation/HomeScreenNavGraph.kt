package com.demo.app.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.demo.app.feature.weather.navigation.weatherScreen
import com.demoapp.feature.records.navigation.recordsScreen

@Composable
internal fun HomeScreenNavGraph(
    navHostController: NavHostController,
    startDestination: String,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        weatherScreen()

        recordsScreen()
    }
}
