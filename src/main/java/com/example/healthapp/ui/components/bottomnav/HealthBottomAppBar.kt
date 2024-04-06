package com.example.healthapp.ui.components.bottomnav

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.healthapp.ui.destinations.HomeScreenDestination
import com.example.healthapp.ui.theme.HealthBlue
import com.ramcosta.composedestinations.navigation.navigate
import com.ramcosta.composedestinations.navigation.popUpTo

@Composable
fun HealthBottomAppBar(navController: NavController) {
    NavigationBar(containerColor = Color.White) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination

        BottomBarDestination.values().forEach { screen ->
            val selected = currentDestination?.route == screen.direction.route
            NavigationBarItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = screen.name
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = screen.label),
                        fontWeight =
                        if (selected)
                            FontWeight.Bold
                        else
                            FontWeight.Normal
                    )
                },
                selected = selected,
                onClick = {
                    navController.navigate(screen.direction) {
                        popUpTo(HomeScreenDestination)
                        launchSingleTop = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = HealthBlue,
                    selectedTextColor = HealthBlue,
                    indicatorColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray
                )
            )
        }
    }
}