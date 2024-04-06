package com.example.healthapp.ui.login

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthapp.data.database.entities.User
import com.example.healthapp.data.repository.Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: Repository
) : ViewModel() {

    val username = mutableStateOf(TextFieldValue(""))
    val password = mutableStateOf(TextFieldValue(""))
    val isUsernameError = mutableStateOf(false)
    val isPasswordError = mutableStateOf(false)

    private val _user = MutableStateFlow<User?>(null)
    val user = _user.asStateFlow()

    fun validateUser() {
        viewModelScope.launch {
            _user.value = repository.validateUser(username.value.text, password.value.text)
            if (user.value == null) {
                isUsernameError.value = true
                isPasswordError.value = true
            }
        }
    }
}