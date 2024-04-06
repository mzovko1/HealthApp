package com.example.healthapp.ui.components.bottomnav

import androidx.annotation.StringRes
import com.example.healthapp.R
import com.example.healthapp.ui.destinations.DailyEntriesScreenDestination
import com.example.healthapp.ui.destinations.HomeScreenDestination
import com.example.healthapp.ui.destinations.UserScreenDestination
import com.ramcosta.composedestinations.spec.Direction

enum class BottomBarDestination(
    val direction: Direction,
    val icon: Int,
    @StringRes val label: Int
) {
    HomeScreen(HomeScreenDestination, R.drawable.home, R.string.home),
    DailyDataScreen(DailyEntriesScreenDestination, R.drawable.daily, R.string.daily),
    UserScreen(UserScreenDestination, R.drawable.user, R.string.user)
}