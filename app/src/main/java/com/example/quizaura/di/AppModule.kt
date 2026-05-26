package com.example.quizaura.di

import androidx.room.Room
import com.example.quizaura.data.AuthService
import com.example.quizaura.data.QuizRepository
import com.example.quizaura.data.TriviaApi
import com.example.quizaura.data.local.QuizDatabase
import com.example.quizaura.presentation.auth.AuthViewModel
import com.example.quizaura.presentation.quiz.QuizViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.androidx.viewmodel.dsl.viewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


val appModule = module{
        single { AuthService() }
        single { Room.databaseBuilder(androidContext(), QuizDatabase::class.java,"quiz_db").build() }
        single { get<QuizDatabase>().questionDao() }
    viewModel{ AuthViewModel(get()) }
    single {
        Retrofit.Builder()
            .baseUrl("https://opentdb.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(TriviaApi::class.java)
    }
    single { QuizRepository(get(), get()) }
    viewModel { QuizViewModel(get()) }
}
