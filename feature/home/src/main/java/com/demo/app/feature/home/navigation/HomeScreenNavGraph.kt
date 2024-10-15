package com.demo.app.feature.home.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
internal fun HomeScreenNavGraph(
    navHostController: NavHostController,
    startDestination: String
) {
    NavHost(
        navController = navHostController,
        startDestination = startDestination
    ) {

    }
}
