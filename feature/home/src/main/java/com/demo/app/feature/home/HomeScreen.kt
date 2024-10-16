package com.demo.app.feature.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.demo.app.feature.home.navigation.HomeScreenNavGraph
import com.demo.app.feature.home.ui.BottomNavigationBar
import com.demo.app.feature.weather.navigation.WEATHER_NAV_ROUTE

@Composable
internal fun HomeScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController)
        }
    ) { innerPadding ->
        HomeScreenNavGraph(
            navController = navController,
            startDestination = WEATHER_NAV_ROUTE,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}
