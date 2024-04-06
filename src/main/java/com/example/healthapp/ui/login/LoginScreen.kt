package com.example.healthapp.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.healthapp.MainViewModel
import com.example.healthapp.R
import com.example.healthapp.ui.components.HealthAppButton
import com.example.healthapp.ui.components.HealthAppTextField
import com.example.healthapp.ui.destinations.HomeScreenDestination
import com.example.healthapp.ui.destinations.LoginScreenDestination
import com.example.healthapp.ui.destinations.SignupScreenDestination
import com.example.healthapp.ui.theme.HealthAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.popUpTo
import org.koin.androidx.compose.koinViewModel

@RootNavGraph(start = true)
@Destination
@Composable
fun LoginScreen(
    navigator: DestinationsNavigator,
    mainViewModel: MainViewModel
) {
    val loginViewModel = koinViewModel<LoginViewModel>()
    val user = loginViewModel.user.collectAsStateWithLifecycle()
    if (user.value != null) {
        mainViewModel.user.value = user.value
        println("LOGIN SUCCESS: $user")
        navigator.navigate(HomeScreenDestination()) {
            popUpTo(LoginScreenDestination){
                inclusive = true
            }
        }
    }
    LoginContent(
        username = loginViewModel.username.value,
        password = loginViewModel.password.value,
        isUsernameError = loginViewModel.isUsernameError.value,
        isPasswordError = loginViewModel.isPasswordError.value,
        onUsernameError = { loginViewModel.isUsernameError.value = true },
        onPasswordError = { loginViewModel.isPasswordError.value = true },
        onUsernameValueChange = {
            loginViewModel.username.value = it
            loginViewModel.isUsernameError.value = false
        },
        onPasswordValueChange = {
            loginViewModel.password.value = it
            loginViewModel.isPasswordError.value = false
        },
        onLoginClick = { loginViewModel.validateUser() },
        onSignupClick = {
            navigator.navigate(
                SignupScreenDestination(
                    loginViewModel.username.value.text,
                    loginViewModel.password.value.text
                )
            )
        }
    )
}

@Composable
private fun LoginContent(
    onLoginClick: () -> Unit,
    username: TextFieldValue,
    password: TextFieldValue,
    isUsernameError: Boolean,
    isPasswordError: Boolean,
    onUsernameError: () -> Unit,
    onPasswordError: () -> Unit,
    onUsernameValueChange: (TextFieldValue) -> Unit,
    onPasswordValueChange: (TextFieldValue) -> Unit,
    onSignupClick: () -> Unit
) {
    val focusManager = LocalFocusManager.current
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(state = scrollState)
            .background(Color.White),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.health_logo),
            contentDescription = null,
            contentScale = ContentScale.Fit
        )

        HealthAppTextField(
            modifier = Modifier
                .imePadding()
                .fillMaxWidth(0.7F)
                .padding(bottom = 5.dp),
            value = username,
            onValueChange = { onUsernameValueChange(it) },
            label = "Username",
            placeholder = "e.g. Matea",
            isError = isUsernameError,
            supportingText = "Invalid username!",
            keyboardType = KeyboardType.Text,
            onKeyboardAction = {
                focusManager.moveFocus(FocusDirection.Next)
            }
        )
        HealthAppTextField(
            modifier = Modifier
                .imePadding()
                .fillMaxWidth(0.7F)
                .padding(bottom = 10.dp),
            value = password,
            onValueChange = { onPasswordValueChange(it) },
            label = "Password",
            placeholder = "e.g. 1234",
            isError = isPasswordError,
            supportingText = "Invalid password!",
            keyboardType = KeyboardType.Password,
            visualTransformation = PasswordVisualTransformation(),
            onKeyboardAction = { focusManager.clearFocus() }
        )

        HealthAppButton(
            modifier = Modifier
                .fillMaxWidth(0.7F)
                .padding(bottom = 5.dp),
            text = "Log in"
        ) {
            if (username.text.isBlank()) onUsernameError()
            else if (password.text.isBlank()) onPasswordError()
            else onLoginClick()
        }

        HealthAppButton(modifier = Modifier
            .fillMaxWidth(0.7F),
            text = "Sign up",
            onClick = {
                if (username.text.isBlank()) onUsernameError()
                else if (password.text.isBlank()) onPasswordError()
                else onSignupClick()
            }
        )
    }
}

@Composable
@Preview
private fun LoginScreenPreview() {
    HealthAppTheme {
        LoginContent(
            username = TextFieldValue("Matea"),
            password = TextFieldValue("1234"),
            isUsernameError = false,
            isPasswordError = false,
            onUsernameError = { },
            onPasswordError = { },
            onUsernameValueChange = { },
            onPasswordValueChange = { },
            onLoginClick = { },
            onSignupClick = { }
        )
    }
}