package com.example.quizaura.presentation

sealed class AuthUiState {
    data object Idle : AuthUiState()
    data object Loading : AuthUiState()
    data object Success : AuthUiState()
    data class Error(
        val msg : String
    ): AuthUiState()
}