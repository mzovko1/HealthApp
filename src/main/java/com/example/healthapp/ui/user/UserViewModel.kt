package com.example.healthapp.ui.user

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.KeyboardType
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.healthapp.data.database.entities.User
import com.example.healthapp.data.repository.Repository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class UserViewModel(
    private val repository: Repository,
    user: User
) : ViewModel() {
    private val userFlow = repository.getUserData(user.username).stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(),
        user
    )

    val uiState = mutableStateOf(
        UserScreenUiState(
            listOf(
                UserInfoItemState("Height: ", "${user.height}cm", KeyboardType.Number),
                UserInfoItemState("Weight: ", "${user.weight}kg", KeyboardType.Number),
                UserInfoItemState("Age: ", user.age.toString(), KeyboardType.Number),
                UserInfoItemState("Gender: ", user.gender, KeyboardType.Text),
                UserInfoItemState("Activity: ", user.activity, KeyboardType.Text),
                UserInfoItemState("Medications: ", user.medications.toString().drop(1).dropLast(1), KeyboardType.Text)
            )
        )
    )

    init {
        viewModelScope.launch {
            userFlow.collect {
                uiState.value = UserScreenUiState(
                    listOf(
                        UserInfoItemState("Height: ", "${it.height}cm", KeyboardType.Number),
                        UserInfoItemState("Weight: ", "${it.weight}kg", KeyboardType.Number),
                        UserInfoItemState("Age: ", it.age.toString(), KeyboardType.Number),
                        UserInfoItemState("Gender: ", it.gender, KeyboardType.Text),
                        UserInfoItemState("Activity: ", it.activity, KeyboardType.Text),
                        UserInfoItemState(
                            "Medications: ",
                            it.medications.toString().drop(1).dropLast(1),
                            KeyboardType.Text
                        )
                    )
                )
            }
        }
    }

    fun updateInfo(newInfo: String, title: String) {
        viewModelScope.launch {
            userFlow.collect {
                val user = when (title) {
                    "Height: " -> it.copy(height = newInfo.toInt())
                    "Weight: " -> it.copy(weight = newInfo.toInt())
                    "Age: " -> it.copy(age = newInfo.toInt())
                    "Gender: " -> it.copy(gender = newInfo)
                    else -> it.copy(medications = newInfo.split(","))
                }
                repository.updateUserInfo(user)
            }
        }
    }
}
