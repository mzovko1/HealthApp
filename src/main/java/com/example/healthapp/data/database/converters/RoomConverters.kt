package com.example.healthapp.data.database.converters

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.time.LocalDate

class RoomConverters {

    @TypeConverter
    fun stringToLocalDate(localDate: String): LocalDate = LocalDate.parse(localDate)

    @TypeConverter
    fun localDateToString(localDate: LocalDate): String = localDate.toString()

    @TypeConverter
    fun stringToList(value: String): List<String> = Json.decodeFromString(value)

    @TypeConverter
    fun listToString(value: List<String>): String = Json.encodeToString(value)
}