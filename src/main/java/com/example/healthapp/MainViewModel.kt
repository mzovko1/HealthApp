package com.example.healthapp

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.healthapp.data.database.entities.User

class MainViewModel : ViewModel() {
    val user = mutableStateOf<User?>(null)
}