package com.example.healthapp.ui.user

import androidx.compose.ui.text.input.KeyboardType

data class UserInfoItemState(
    val title: String,
    val content: String,
    val type: KeyboardType
)

data class UserScreenUiState(
    val userData: List<UserInfoItemState>
)