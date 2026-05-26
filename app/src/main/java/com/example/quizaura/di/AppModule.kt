package com.example.quizaura.di

import androidx.room.Room
import com.example.quizaura.data.AuthService
import com.example.quizaura.data.QuizRepository
import com.example.quizaura.data.local.QuizDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module


val appModule = module{
        single { AuthService() }
        single { Room.databaseBuilder(androidContext(), QuizDatabase::class.java,"quiz_db").build() }
        single { get<QuizDatabase>().questionDao() }
    single { QuizRepository(androidContext(), get()) }
}
