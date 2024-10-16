package com.demo.app.feature.home.model

import androidx.annotation.DrawableRes
import com.demo.app.core.design.R
import com.demo.app.feature.weather.navigation.WEATHER_NAV_ROUTE
import com.demoapp.feature.records.navigation.RECORDS_NAV_ROUTE

enum class BottomNavItem(
    val route: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val title: String
) {
    Weather(
        route = WEATHER_NAV_ROUTE,
        selectedIcon = R.drawable.ic_sunny_filled_24,
        unselectedIcon = R.drawable.ic_sunny_outline_24,
        title = "Current Weather"
    ),
    Records(
        route = RECORDS_NAV_ROUTE,
        selectedIcon = R.drawable.ic_event_note_filled_24,
        unselectedIcon = R.drawable.ic_event_note_outline_24,
        title = "Temperature Records"
    )
}