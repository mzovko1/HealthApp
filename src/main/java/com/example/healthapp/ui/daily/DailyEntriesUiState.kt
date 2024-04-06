package com.example.healthapp.ui.daily

import com.example.healthapp.data.database.entities.UserDailyData

sealed class DailyEntriesUiState {
    object NoData: DailyEntriesUiState()
    data class Data(val dailyEntries: List<UserDailyData>): DailyEntriesUiState()
}
