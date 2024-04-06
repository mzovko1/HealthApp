package com.example.healthapp.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.Companion.CASCADE
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(
    tableName = "user_daily_data",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["username"],
        childColumns = ["user"],
        onDelete = CASCADE
    )]
)
data class UserDailyData(
    @PrimaryKey
    val date: LocalDate,
    @ColumnInfo(index = true)
    val user: String,
    val water: Int,
    val bloodPressure: String,
    val steps: Int,
    val sleep: Int,
    val workouts: List<String>?
)
