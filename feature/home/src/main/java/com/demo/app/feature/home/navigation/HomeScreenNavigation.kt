package com.demo.app.feature.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.demo.app.feature.core.util.OnNavResult
import com.demo.app.feature.home.HomeScreen

const val HOME_NAV_ROUTE = "home_nav_route"

sealed class HomeNavResult {
    data object MoveToLogin: HomeNavResult()
}

fun NavController.navigateToHome() {
    navigate(HOME_NAV_ROUTE)
}

fun NavGraphBuilder.homeScreen(onNavResult: OnNavResult<HomeNavResult>) {
    composable(
        route = HOME_NAV_ROUTE
    ) {
        HomeScreen(onNavResult = onNavResult)
    }
}
