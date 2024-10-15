package com.demo.app.feature.home.model

import androidx.annotation.DrawableRes
import com.demo.app.core.design.R

enum class BottomNavItem(
    val route: String,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
    val title: String
) {
    Weather(
        route = "",
        selectedIcon = R.drawable.ic_sunny_filled_24,
        unselectedIcon = R.drawable.ic_sunny_outline_24,
        title = "Current Weather"
    ),
    Records(
        route = "",
        selectedIcon = R.drawable.ic_event_note_filled_24,
        unselectedIcon = R.drawable.ic_event_note_outline_24,
        title = "Temperature Records"
    )
}