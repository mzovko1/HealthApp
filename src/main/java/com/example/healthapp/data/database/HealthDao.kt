package com.example.healthapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.healthapp.data.database.entities.User
import com.example.healthapp.data.database.entities.UserDailyData
import kotlinx.coroutines.flow.Flow

@Dao
interface HealthDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDailyUserData(userDailyData: UserDailyData): Long

    @Query("SELECT * FROM user WHERE username LIKE :username AND password LIKE :password")
    suspend fun validateUser(username: String, password: String): User?

    @Query("SELECT * FROM user_daily_data WHERE user LIKE :username AND date LIKE :date")
    fun getDailyData(username: String, date: String): Flow<UserDailyData?>

    @Query("SELECT * FROM user WHERE username LIKE :username")
    fun getUserData(username: String): Flow<User>

    @Query("SELECT * FROM user_daily_data WHERE user LIKE :username")
    fun getAllDailyEntries(username: String): Flow<List<UserDailyData>>

    @Update
    suspend fun updateUserInfo(user: User)
}