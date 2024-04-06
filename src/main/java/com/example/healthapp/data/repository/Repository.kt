package com.example.healthapp.data.repository

import com.example.healthapp.data.database.HealthDao
import com.example.healthapp.data.database.entities.User
import com.example.healthapp.data.database.entities.UserDailyData
import kotlinx.coroutines.flow.Flow

class Repository(
    private val healthDao: HealthDao
) {
    suspend fun validateUser(username: String, password: String): User? {
        return healthDao.validateUser(username, password)
    }

    suspend fun insertUser(user: User) {
        healthDao.insertUser(user)
    }

    fun getUserDailyData(username: String, date: String): Flow<UserDailyData?> {
        return healthDao.getDailyData(username, date)
    }

    suspend fun insertDailyUserData(userDailyData: UserDailyData){
        healthDao.insertDailyUserData(userDailyData)
    }

    fun getAllDailyEntries(username: String): Flow<List<UserDailyData>> {
        return healthDao.getAllDailyEntries(username)
    }

    fun getUserData(username: String): Flow<User>{
        return healthDao.getUserData(username)
    }

    suspend fun updateUserInfo(user: User){
        healthDao.updateUserInfo(user)
    }
}