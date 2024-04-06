package com.example.healthapp.ui.input

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthapp.data.database.entities.UserDailyData
import com.example.healthapp.data.repository.Repository
import kotlinx.coroutines.launch
import java.time.LocalDate

class InputDataViewModel(
    private val repository: Repository,
    private val username: String
) : ViewModel() {
    val waterPagerItemInput = mutableStateOf(TextFieldValue(""))
    val bloodPressurePagerItemInput = mutableStateOf(TextFieldValue(""))
    val stepsPagerItemInput = mutableStateOf(TextFieldValue(""))
    val sleepPagerItemInput = mutableStateOf(TextFieldValue(""))
    val workoutsPagerItemInput = mutableStateOf(TextFieldValue(""))

    val isWaterError = mutableStateOf(false)
    val isBloodPressureError = mutableStateOf(false)
    val isStepsError = mutableStateOf(false)
    val isSleepError = mutableStateOf(false)
    val isWorkoutsError = mutableStateOf(false)

    fun insertDailyUserData() {
        val dailyUserData = UserDailyData(
            user = username,
            date = LocalDate.now(),
            water = waterPagerItemInput.value.text.toInt(),
            bloodPressure = bloodPressurePagerItemInput.value.text,
            steps = stepsPagerItemInput.value.text.toInt(),
            sleep = sleepPagerItemInput.value.text.toInt(),
            workouts =
            if (workoutsPagerItemInput.value.text.isNotBlank())
                workoutsPagerItemInput.value.text.split(",")
            else
                null

        )
        viewModelScope.launch {
            repository.insertDailyUserData(dailyUserData)
        }
    }

}