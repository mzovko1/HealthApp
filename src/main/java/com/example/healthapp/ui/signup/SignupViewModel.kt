package com.example.healthapp.ui.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthapp.data.database.entities.User
import com.example.healthapp.data.repository.Repository
import kotlinx.coroutines.launch

class SignupViewModel(
    private val repository: Repository,
    val username: String,
    val password: String
) : ViewModel() {

    val weight = mutableStateOf(TextFieldValue(""))
    val height = mutableStateOf(TextFieldValue(""))
    val age = mutableStateOf(TextFieldValue(""))
    val gender = mutableStateOf(TextFieldValue(""))
    val activity = mutableStateOf(1)
    val medication = mutableStateOf(TextFieldValue(""))

    val isWeightError = mutableStateOf(false)
    val isHeightError = mutableStateOf(false)
    val isAgeError = mutableStateOf(false)
    val isGenderError = mutableStateOf(false)

    fun insertUser(): User {
        val userActivity = when(activity.value){
            1 -> "Low"
            2 -> "Medium"
            else -> "High"
        }
        val user = User(
            username = username,
            password = password,
            weight = weight.value.text.toInt(),
            height = height.value.text.toInt(),
            activity = userActivity,
            age = age.value.text.toInt(),
            gender = gender.value.text,
            medications = medication.value.text.split(",")
        )
        viewModelScope.launch {
            repository.insertUser(user)
        }

        return user
    }
}