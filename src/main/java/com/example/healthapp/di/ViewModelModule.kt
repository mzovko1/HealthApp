package com.example.healthapp.di

import com.example.healthapp.MainViewModel
import com.example.healthapp.ui.daily.DailyEntriesViewModel
import com.example.healthapp.ui.home.HomeScreenViewModel
import com.example.healthapp.ui.input.InputDataViewModel
import com.example.healthapp.ui.login.LoginViewModel
import com.example.healthapp.ui.signup.SignupViewModel
import com.example.healthapp.ui.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::MainViewModel)
    viewModelOf(::LoginViewModel)
    viewModelOf(::SignupViewModel)
    viewModelOf(::HomeScreenViewModel)
    viewModelOf(::InputDataViewModel)
    viewModelOf(::DailyEntriesViewModel)
    viewModelOf(::UserViewModel)
}