package com.example.quizaura.data


data class QuizResult(
    val totalQuestions: Int,
    val correctAnswers: Int,
    val wrongAnswers: Int,
    val percentage: Float,
    val timeTaken: Long
)