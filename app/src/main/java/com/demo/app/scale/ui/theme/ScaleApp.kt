package com.demo.app.scale.ui.theme

import androidx.compose.runtime.Composable
import com.demo.app.feature.login.navigation.LOGIN_NAV_ROUTE
import com.demo.app.scale.navigation.ScaleNavGraph

const val START_DESTINATION = LOGIN_NAV_ROUTE

@Composable
fun ScaleApp() {
    ScaleTheme {
        ScaleNavGraph(
            startDestination = START_DESTINATION
        )
    }
}
