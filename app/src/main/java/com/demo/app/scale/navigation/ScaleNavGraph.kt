package com.demo.app.scale.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.demo.app.feature.home.navigation.HomeNavResult
import com.demo.app.feature.home.navigation.homeScreen
import com.demo.app.feature.home.navigation.navigateToHome
import com.demo.app.feature.login.navigation.LoginNavResult
import com.demo.app.feature.login.navigation.loginScreen

@Composable
fun ScaleNavGraph(
    startDestination: String
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        loginScreen(
            onNavResult = { res ->
                when (res) {
                    LoginNavResult.Close -> {
                        // TODO: close app
                    }

                    LoginNavResult.MoveToHomeScreen -> {
                        navController.navigateToHome()
                    }
                }
            }
        )

        homeScreen(
            onNavResult = { res ->
                when (res) {
                    HomeNavResult.MoveToLogin -> {
                        navController.popBackStack()
                    }
                }
            }
        )
    }
}
