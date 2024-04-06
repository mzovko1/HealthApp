package com.example.healthapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthapp.R
import com.example.healthapp.data.database.entities.UserDailyData
import com.example.healthapp.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDate

class HomeScreenViewModel(
    repository: Repository,
    username: String,
    date: LocalDate
) : ViewModel() {

    private val _dailyData = repository.getUserDailyData(username, date.toString()).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        null
    )

    private val _uiState = MutableStateFlow<HomeScreenUiState>(HomeScreenUiState.NoData)
    val uiState = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            _dailyData.collect {
                _uiState.value = when (it) {
                    null -> HomeScreenUiState.NoData
                    else -> HomeScreenUiState.Data(it)
                }
            }
        }
    }

    fun prepUiData(data: UserDailyData): List<Triple<String, String, Int?>> {
        val bloodPressure = data.bloodPressure.split("/")
        val waterIcon = if (data.water < 2) R.drawable.warning else R.drawable.check
        val bloodPressureIcon =
            if (bloodPressure[0].toInt() in 90..120 && bloodPressure[1].toInt() in 60..80)
                R.drawable.check
            else
                R.drawable.warning
        val sleepIcon = if (data.sleep < 7) R.drawable.warning else R.drawable.check
        val stepsIcon = if (data.steps < 10000) R.drawable.warning else R.drawable.check
        (data.workouts != null)
        val list: MutableList<Triple<String, String, Int?>> = mutableListOf(
            Triple("Water", "${data.water}l", waterIcon),
            Triple("Blood Pressure", data.bloodPressure, bloodPressureIcon),
            Triple("Steps", "${data.steps}", stepsIcon),
            Triple("Sleep", "${data.sleep}h", sleepIcon)
        )
        if (data.workouts != null)
            list.add(Triple("Workouts", data.workouts.toString().drop(1).dropLast(1), null))
        return list
    }
}