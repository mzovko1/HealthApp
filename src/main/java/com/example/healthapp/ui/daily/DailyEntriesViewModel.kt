package com.example.healthapp.ui.daily

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthapp.data.database.entities.UserDailyData
import com.example.healthapp.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DailyEntriesViewModel(
    repository: Repository,
    username: String
): ViewModel() {

    private val _dailyEntries = repository.getAllDailyEntries(username).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        emptyList()
    )

    private val _uiState = MutableStateFlow<DailyEntriesUiState>(DailyEntriesUiState.NoData)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _dailyEntries.collect {
                _uiState.value = when(it){
                    emptyList<UserDailyData>() -> DailyEntriesUiState.NoData
                    else -> DailyEntriesUiState.Data(it)
                }
            }
        }
    }
}