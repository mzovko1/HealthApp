package com.example.healthapp.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey
    val username: String,
    val password: String,
    val height: Int,
    val weight: Int,
    val age: Int,
    val gender: String,
    val activity: String,
    val medications: List<String>
)