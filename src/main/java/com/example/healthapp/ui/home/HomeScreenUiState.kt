package com.example.healthapp.ui.home

import com.example.healthapp.data.database.entities.UserDailyData

sealed class HomeScreenUiState {
    object NoData: HomeScreenUiState()
    data class Data(val dailyData: UserDailyData): HomeScreenUiState()
}