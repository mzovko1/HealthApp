package com.example.healthapp

import android.app.Application
import com.example.healthapp.di.databaseModule
import com.example.healthapp.di.repositoryModule
import com.example.healthapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class HealthApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@HealthApplication)
            modules(
                databaseModule,
                viewModelModule,
                repositoryModule
            )
        }
    }
}