package com.example.healthapp.ui.input

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.healthapp.MainViewModel
import com.example.healthapp.R
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Destination
@Composable
fun InputDataScreen(
    navigator: DestinationsNavigator,
    mainViewModel: MainViewModel
) {
    val inputViewModel = koinViewModel<InputDataViewModel> { parametersOf(mainViewModel.user.value?.username) }
    InputDataScreenContent(
        waterInput = inputViewModel.waterPagerItemInput.value,
        isWaterError = inputViewModel.isWaterError.value,
        onWaterValueChange = {
            inputViewModel.waterPagerItemInput.value = it
            if (inputViewModel.isWaterError.value) inputViewModel.isWaterError.value = false
        },
        onWaterError = { inputViewModel.isWaterError.value = true },
        bloodPressureInput = inputViewModel.bloodPressurePagerItemInput.value,
        isBloodPressureError = inputViewModel.isBloodPressureError.value,
        onBloodPressureValueChange = {
            inputViewModel.bloodPressurePagerItemInput.value = it
            if (inputViewModel.isBloodPressureError.value) inputViewModel.isBloodPressureError.value =
                false
        },
        onBloodPressureError = { inputViewModel.isBloodPressureError.value = true },
        stepsInput = inputViewModel.stepsPagerItemInput.value,
        isStepsError = inputViewModel.isStepsError.value,
        onStepsValueChange = {
            inputViewModel.stepsPagerItemInput.value = it
            if (inputViewModel.isStepsError.value) inputViewModel.isStepsError.value = false
        },
        onStepsError = { inputViewModel.isStepsError.value = true },
        sleepInput = inputViewModel.sleepPagerItemInput.value,
        isSleepError = inputViewModel.isSleepError.value,
        onSleepValueChange = {
            inputViewModel.sleepPagerItemInput.value = it
            if (inputViewModel.isSleepError.value) inputViewModel.isSleepError.value = false
        },
        onSleepError = { inputViewModel.isSleepError.value = true },
        workoutsInput = inputViewModel.workoutsPagerItemInput.value,
        isWorkoutsError = inputViewModel.isWorkoutsError.value,
        onWorkoutsValueChange = {
            inputViewModel.workoutsPagerItemInput.value = it
            if (inputViewModel.isWorkoutsError.value) inputViewModel.isWorkoutsError.value = false
        },
        onWorkoutsError = { inputViewModel.isWorkoutsError.value = true },
        onFinalPagerButtonClick = {
            inputViewModel.insertDailyUserData()
            navigator.navigateUp()
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun InputDataScreenContent(
    onFinalPagerButtonClick: () -> Unit,
    waterInput: TextFieldValue,
    isWaterError: Boolean,
    onWaterValueChange: (TextFieldValue) -> Unit,
    onWaterError: () -> Unit,
    bloodPressureInput: TextFieldValue,
    isBloodPressureError: Boolean,
    onBloodPressureValueChange: (TextFieldValue) -> Unit,
    onBloodPressureError: () -> Unit,
    stepsInput: TextFieldValue,
    isStepsError: Boolean,
    onStepsValueChange: (TextFieldValue) -> Unit,
    onStepsError: () -> Unit,
    sleepInput: TextFieldValue,
    isSleepError: Boolean,
    onSleepValueChange: (TextFieldValue) -> Unit,
    onSleepError: () -> Unit,
    workoutsInput: TextFieldValue,
    isWorkoutsError: Boolean,
    onWorkoutsValueChange: (TextFieldValue) -> Unit,
    onWorkoutsError: () -> Unit,
) {
    val pagerState = rememberPagerState()
    val scope = rememberCoroutineScope()
    val nextPage = {
        scope.launch {
            pagerState.scrollToPage(
                pagerState.currentPage + 1
            )
        }
    }
    val pagerItems = (1..5).map { item ->
        InputDataPagerItemState(
            title = when (item) {
                1 -> "Water"
                2 -> "Blood Pressure"
                3 -> "Steps"
                4 -> "Sleep"
                else -> "Workouts"
            },
            input = when (item) {
                1 -> waterInput
                2 -> bloodPressureInput
                3 -> stepsInput
                4 -> sleepInput
                else -> workoutsInput
            },
            image = when (item) {
                1 -> R.drawable.water
                2 -> R.drawable.blood_pressure
                3 -> R.drawable.steps
                4 -> R.drawable.sleep
                else -> R.drawable.workouts
            },
            keyboardType = when (item) {
                1 -> KeyboardType.Number
                2 -> KeyboardType.Text
                3 -> KeyboardType.Number
                4 -> KeyboardType.Number
                else -> KeyboardType.Text
            },
            onButtonClick = {
                when (item) {
                    5 -> {
                        onFinalPagerButtonClick()
                    }

                    else -> {
                        scope.launch {
                            pagerState.scrollToPage(
                                pagerState.currentPage + 1
                            )
                        }
                    }
                }
            },
            onInputChange = { input ->
                when (item) {
                    1 -> onWaterValueChange(input)
                    2 -> onBloodPressureValueChange(input)
                    3 -> onStepsValueChange(input)
                    4 -> onSleepValueChange(input)
                    else -> onWorkoutsValueChange(input)
                }
            },
            isInputError = when (item) {
                1 -> isWaterError
                2 -> isBloodPressureError
                3 -> isStepsError
                4 -> isSleepError
                else -> isWorkoutsError
            },
            onKeyboardAction = {
                when (item) {
                    1 -> {
                        if (waterInput.text.isBlank()) onWaterError()
                        else nextPage()
                    }

                    2 -> {
                        if (bloodPressureInput.text.isBlank()) onBloodPressureError()
                        else nextPage()
                    }

                    3 -> {
                        if (stepsInput.text.isBlank()) onStepsError()
                        else nextPage()
                    }

                    4 -> {
                        if (sleepInput.text.isBlank()) onSleepError()
                        else nextPage()
                    }

                    5 -> {
                        if (workoutsInput.text.isBlank()) onWorkoutsError()
                        onFinalPagerButtonClick()
                    }
                }
            },
            buttonText = when(item){
                5 -> "Finish"
                else -> "Next"
            }
        )
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        HorizontalPager(
            pageCount = pagerItems.size,
            state = pagerState,
            userScrollEnabled = false
        ) {
            InputDataPagerItem(state = pagerItems[it])
        }
        Row(
            Modifier
                .height(50.dp)
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
                .imePadding(),
            horizontalArrangement = Arrangement.Center
        ) {
            repeat(pagerItems.size) { iteration ->
                val color =
                    if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                Box(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(CircleShape)
                        .background(color)
                        .size(10.dp)
                )
            }
        }
    }
}
