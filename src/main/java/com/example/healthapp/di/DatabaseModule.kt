package com.example.healthapp.di

import androidx.room.Room
import com.example.healthapp.data.database.HealthDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            HealthDatabase::class.java,
            "health_database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<HealthDatabase>().getHealthDao()
    }
}