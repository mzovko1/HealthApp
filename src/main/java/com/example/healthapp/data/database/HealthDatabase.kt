package com.example.healthapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.healthapp.data.database.converters.RoomConverters
import com.example.healthapp.data.database.entities.User
import com.example.healthapp.data.database.entities.UserDailyData

@Database(entities = [User::class, UserDailyData::class], version = 6, exportSchema = false)
@TypeConverters(RoomConverters::class)
abstract class HealthDatabase: RoomDatabase() {
    abstract fun getHealthDao(): HealthDao
}