package com.demo.app.feature.login.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.demo.app.feature.core.util.OnNavResult
import com.demo.app.feature.login.LoginRoute

const val LOGIN_NAV_ROUTE = "login_nav_route"

sealed interface LoginNavResult {
    data object Close: LoginNavResult
    data object MoveToHomeScreen: LoginNavResult
}

sealed interface LoginScreenAction {
    data object OnSwitchScreen: LoginScreenAction
    data class OnLoginRequest(val username: String, val password: String): LoginScreenAction
    data class OnSignUpRequest(val username: String, val password: String): LoginScreenAction
}

fun NavController.navigateToLogin() {
    navigate(LOGIN_NAV_ROUTE)
}

fun NavGraphBuilder.loginScreen(onNavResult: OnNavResult<LoginNavResult>) {
    composable(
        route = LOGIN_NAV_ROUTE
    ) {
        LoginRoute(onNavResult = onNavResult)
    }
}
