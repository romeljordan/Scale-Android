package com.demo.app.feature.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.demo.app.feature.core.OnNavResult
import com.demo.app.feature.login.LoginScreen

const val LOGIN_NAV_ROUTE = "login_nav_route"

sealed class LoginNavResult {
    data object Close: LoginNavResult()
}

fun NavController.navigateToLogin() {
    navigate(LOGIN_NAV_ROUTE)
}

fun NavGraphBuilder.loginScreen(onNavResult: OnNavResult<LoginNavResult>) {
    composable(
        route = LOGIN_NAV_ROUTE
    ) {
        LoginScreen()
    }
}
