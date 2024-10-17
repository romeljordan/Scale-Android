package com.demo.app.feature.home

import android.Manifest
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.demo.app.core.design.composable.PermissionUi
import com.demo.app.feature.home.navigation.HomeScreenNavGraph
import com.demo.app.feature.home.ui.BottomNavigationBar
import com.demo.app.feature.weather.navigation.WEATHER_NAV_ROUTE
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalPermissionsApi::class)
@Composable
internal fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    val permissionState = rememberPermissionState(Manifest.permission.ACCESS_FINE_LOCATION)

    if (permissionState.status.isGranted) {
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
    } else {
        GrantLocationPermission()
    }

    PermissionUi(Manifest.permission.ACCESS_FINE_LOCATION) {
        /* no-op */
    }

    LaunchedEffect(permissionState.status.isGranted) {
        if (permissionState.status.isGranted) {
           // TODO: add back viewModel.fetchAndLogWeather()
        }
    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    HomeScreen()
}
