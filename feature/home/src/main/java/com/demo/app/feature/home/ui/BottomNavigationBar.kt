package com.demo.app.feature.home.ui

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.demo.app.feature.home.model.BottomNavItem

@Composable
internal fun BottomNavigationBar(navController: NavHostController) {
    NavigationBar {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        BottomNavItem.entries.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationBarItem(
                selected = false,
                icon = {
                    Icon(
                        painter = painterResource(
                            if (isSelected) item.selectedIcon else item.unselectedIcon
                        ),
                        contentDescription = null
                    )
                },
                onClick = {
                    // TODO: add navigation
                }
            )
        }
    }
}
