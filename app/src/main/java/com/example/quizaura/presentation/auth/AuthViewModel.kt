package com.example.quizaura.presentation.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizaura.data.AuthService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AuthViewModel (private val authService: AuthService): ViewModel(){
    private val _uiState = MutableStateFlow<AuthUiState>(AuthUiState.Idle)
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()

    fun login(email : String, password : String){
        viewModelScope.launch {
            _uiState.value = AuthUiState.Loading
            val result = authService.login(email,password)
            _uiState.value = if(result.isSuccess){
                AuthUiState.Success
            }else{
                AuthUiState.Error(result.exceptionOrNull()?.message?:"Unknown Error")
            }
        }
    }

    fun signup(email: String,password: String){
        viewModelScope.launch {
            _uiState.value  = AuthUiState.Loading
            val result = authService.signup(email,password)
            _uiState.value = if(result.isSuccess){
                AuthUiState.Success
            }else{
                AuthUiState.Error(result.exceptionOrNull()?.message?:"Unknown Error")
            }
        }
    }
}