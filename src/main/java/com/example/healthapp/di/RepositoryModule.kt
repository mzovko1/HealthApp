package com.example.healthapp.di

import com.example.healthapp.data.repository.Repository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::Repository)
}