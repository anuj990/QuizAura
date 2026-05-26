package com.example.quizaura.di

import androidx.room.Room
import com.example.quizaura.data.AuthService
import com.example.quizaura.data.QuizRepository
import com.example.quizaura.data.local.QuizDatabase
import com.example.quizaura.presentation.auth.AuthViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel  // ← this is Koin's viewModel


val appModule = module{
        single { AuthService() }
        single { Room.databaseBuilder(androidContext(), QuizDatabase::class.java,"quiz_db").build() }
        single { get<QuizDatabase>().questionDao() }
    single { QuizRepository(androidContext(), get()) }
    viewModel{ AuthViewModel(get()) }
}
