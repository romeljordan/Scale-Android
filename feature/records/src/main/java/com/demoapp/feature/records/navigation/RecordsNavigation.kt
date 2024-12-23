package com.demoapp.feature.records.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.demoapp.feature.records.RecordsRoute

const val RECORDS_NAV_ROUTE = "records_route"

fun NavController.navigateToRecords() {
    navigate(
        route = RECORDS_NAV_ROUTE
    )
}

fun NavGraphBuilder.recordsScreen() {
    composable(
        route = RECORDS_NAV_ROUTE
    ) {
        RecordsRoute()
    }
}
