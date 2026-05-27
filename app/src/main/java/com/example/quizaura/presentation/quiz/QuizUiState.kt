package com.example.quizaura.presentation.quiz

import com.example.quizaura.data.Question

data class QuizUiState(
    val questions: List<Question> = emptyList(),
    val currentIndex: Int = 0,
    val selectedOption: Int? = null,
    val answers: List<Int?> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val isFinished: Boolean = false,
    val timeLeft: Int = 30,
    val score : Int = 0
)