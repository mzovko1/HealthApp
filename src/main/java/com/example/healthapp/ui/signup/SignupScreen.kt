package com.example.healthapp.ui.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.healthapp.MainViewModel
import com.example.healthapp.ui.components.HealthAppButton
import com.example.healthapp.ui.components.HealthAppTextField
import com.example.healthapp.ui.destinations.HomeScreenDestination
import com.example.healthapp.ui.destinations.LoginScreenDestination
import com.example.healthapp.ui.destinations.SignupScreenDestination
import com.example.healthapp.ui.theme.HealthAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Destination
@Composable
fun SignupScreen(
    navigator: DestinationsNavigator,
    mainViewModel: MainViewModel,
    username: String,
    password: String
) {
    val signupViewModel = koinViewModel<SignupViewModel> { parametersOf(username, password) }
    SignupContent(
        username = username,
        weight = signupViewModel.weight.value,
        height = signupViewModel.height.value,
        age = signupViewModel.age.value,
        gender = signupViewModel.gender.value,
        activity = signupViewModel.activity.value,
        medication = signupViewModel.medication.value,
        isHeightError = signupViewModel.isHeightError.value,
        isWeightError = signupViewModel.isWeightError.value,
        isAgeError = signupViewModel.isAgeError.value,
        isGenderError = signupViewModel.isGenderError.value,
        onWeightError = { signupViewModel.isWeightError.value = true },
        onHeightError = { signupViewModel.isHeightError.value = true },
        onAgeError = { signupViewModel.isAgeError.value = true },
        onGenderError = { signupViewModel.isGenderError.value = true },
        onWeightValueChange = {
            signupViewModel.weight.value = it
            if (signupViewModel.isWeightError.value)
                signupViewModel.isWeightError.value = false
        },
        onHeightValueChange = {
            signupViewModel.height.value = it
            if (signupViewModel.isHeightError.value)
                signupViewModel.isHeightError.value = false
        },
        onAgeValueChange = {
            signupViewModel.age.value = it
            if (signupViewModel.isAgeError.value)
                signupViewModel.isAgeError.value = false
        },
        onGenderValueChange = {
            signupViewModel.gender.value = it
            if (signupViewModel.isGenderError.value)
                signupViewModel.isGenderError.value = false
        },
        onMedicationValueChange = { signupViewModel.medication.value = it },
        onRadioButtonClick = { signupViewModel.activity.value = it },
        onSignupClick = {
            val user = signupViewModel.insertUser()
            mainViewModel.user.value = user
            navigator.navigate(HomeScreenDestination()){
                popUpTo(SignupScreenDestination){
                    inclusive = true
                }
                popUpTo(LoginScreenDestination){
                    inclusive = true
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun SignupContent(
    username: String,
    weight: TextFieldValue,
    height: TextFieldValue,
    age: TextFieldValue,
    gender: TextFieldValue,
    medication: TextFieldValue,
    activity: Int,
    onWeightValueChange: (TextFieldValue) -> Unit,
    onHeightValueChange: (TextFieldValue) -> Unit,
    onAgeValueChange: (TextFieldValue) -> Unit,
    onGenderValueChange: (TextFieldValue) -> Unit,
    onMedicationValueChange: (TextFieldValue) -> Unit,
    isWeightError: Boolean,
    isHeightError: Boolean,
    isAgeError: Boolean,
    isGenderError: Boolean,
    onWeightError: () -> Unit,
    onHeightError: () -> Unit,
    onAgeError: () -> Unit,
    onGenderError: () -> Unit,
    onRadioButtonClick: (Int) -> Unit,
    onSignupClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    val activityOptions = listOf(1, 2, 3)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = username
                    )
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            HealthAppTextField(
                modifier = Modifier
                    .imePadding()
                    .fillMaxWidth(0.9F)
                    .padding(bottom = 5.dp),
                value = height,
                onValueChange = { onHeightValueChange(it) },
                label = "Height",
                placeholder = "e.g. 190(in cm)",
                isError = isHeightError,
                supportingText = "Invalid height!",
                keyboardType = KeyboardType.Number,
                onKeyboardAction = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            )
            HealthAppTextField(
                modifier = Modifier
                    .imePadding()
                    .fillMaxWidth(0.9F)
                    .padding(bottom = 5.dp),
                value = weight,
                onValueChange = { onWeightValueChange(it) },
                label = "Weight",
                placeholder = "e.g. 70(in kg)",
                isError = isWeightError,
                supportingText = "Invalid weight!",
                keyboardType = KeyboardType.Number,
                onKeyboardAction = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            )
            HealthAppTextField(
                modifier = Modifier
                    .imePadding()
                    .fillMaxWidth(0.9F)
                    .padding(bottom = 5.dp),
                value = age,
                onValueChange = { onAgeValueChange(it) },
                label = "Age",
                placeholder = "e.g. 27(in years)",
                isError = isAgeError,
                supportingText = "Invalid age!",
                keyboardType = KeyboardType.Number,
                onKeyboardAction = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            )
            HealthAppTextField(
                modifier = Modifier
                    .imePadding()
                    .fillMaxWidth(0.9F)
                    .padding(bottom = 5.dp),
                value = gender,
                onValueChange = { onGenderValueChange(it) },
                label = "Gender",
                placeholder = "e.g. M(for male), F(for female)",
                isError = isGenderError,
                supportingText = "Invalid gender!",
                keyboardType = KeyboardType.Text,
                onKeyboardAction = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            )

            Column(
                modifier = Modifier.fillMaxSize(0.9F)
            ) {
                Text(text = "Daily activity:")
                activityOptions.forEach { option ->
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .selectable(
                                selected = activity == option,
                                onClick = { onRadioButtonClick(option) }
                            ),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = activity == option,
                            onClick = { onRadioButtonClick(option) }
                        )
                        Text(
                            text = when (option) {
                                1 -> "Low"
                                2 -> "Medium"
                                else -> "High"
                            }
                        )
                    }
                }
            }

            HealthAppTextField(
                modifier = Modifier
                    .imePadding()
                    .fillMaxWidth(0.9F)
                    .padding(bottom = 5.dp),
                value = medication,
                onValueChange = { onMedicationValueChange(it) },
                label = "Medication",
                placeholder = "e.g. Ibuprofen, Aspirin...",
                keyboardType = KeyboardType.Text,
                onKeyboardAction = {
                    focusManager.clearFocus()
                }
            )
            Spacer(modifier = Modifier.weight(1F))
            HealthAppButton(
                modifier = Modifier
                    .fillMaxWidth(0.9F)
                    .padding(vertical = 16.dp),
                text = "Sign up",
                onClick = {
                    if (weight.text.isBlank()) onWeightError()
                    else if (height.text.isBlank()) onHeightError()
                    else if (age.text.isBlank()) onAgeError()
                    else if (gender.text.isBlank()) onGenderError()
                    else onSignupClick()
                }
            )
        }
    }
}

@Composable
@Preview
private fun SignUpScreenPreview() {
    HealthAppTheme {
        SignupContent(
            username = "Test",
            weight = TextFieldValue("70"),
            height = TextFieldValue("190"),
            age = TextFieldValue("27"),
            gender = TextFieldValue("F"),
            medication = TextFieldValue(""),
            activity = 2,
            onWeightValueChange = { },
            onHeightValueChange = { },
            onAgeValueChange = { },
            onGenderValueChange = { },
            onMedicationValueChange = { },
            isWeightError = false,
            isHeightError = false,
            isAgeError = false,
            isGenderError = false,
            onWeightError = { },
            onHeightError = { },
            onAgeError = { },
            onGenderError = { },
            onRadioButtonClick = { },
            onSignupClick = { }
        )
    }
}