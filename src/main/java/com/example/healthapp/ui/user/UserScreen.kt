package com.example.healthapp.ui.user

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.healthapp.MainViewModel
import com.ramcosta.composedestinations.annotation.Destination
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Destination
@Composable
fun UserScreen(
    mainViewModel: MainViewModel
) {
    val userViewModel = koinViewModel<UserViewModel> { parametersOf(mainViewModel.user.value) }
    val uiState = userViewModel.uiState.value
    UserContent(
        uiState = uiState,
        onInfoChange = { info, title ->
            userViewModel.updateInfo(info, title)
        })
}

@Composable
private fun UserContent(
    uiState: UserScreenUiState,
    onInfoChange: (String, String) -> Unit
) {
    var showAlert by remember { mutableStateOf(false) }
    var clickedEntry by remember { mutableStateOf(uiState.userData[0]) }
    if (showAlert)
        UserInfoDialog(
            entry = clickedEntry,
            setShowDialog = { showAlert = it },
            onInfoChange = onInfoChange
        )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        uiState.userData.forEach { item ->
            UserInfoItem(
                title = item.title, content = item.content
            ) {
                clickedEntry = item
                showAlert = true
            }
        }
    }
}