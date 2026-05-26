package com.example.quizaura.presentation.quiz


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quizaura.data.QuizRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class QuizViewModel(private val repository: QuizRepository) : ViewModel() {

    private val _uiState = MutableStateFlow(QuizUiState())
    val uiState: StateFlow<QuizUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

    init {
        loadQuestions()
    }

    private fun loadQuestions() {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true) }
            val result = repository.getQuestions()
            if (result.isSuccess) {
                val questions = result.getOrDefault(emptyList())
                _uiState.update {
                    it.copy(
                        questions = questions,
                        answers = List(questions.size) { null },
                        isLoading = false
                    )
                }
                startTimer()
            } else {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = result.exceptionOrNull()?.message ?: "Failed to load questions"
                    )
                }
            }
        }
    }

    fun selectOption(index: Int) {
        _uiState.update { state ->
            val updatedAnswers = state.answers.toMutableList()
            updatedAnswers[state.currentIndex] = index
            state.copy(
                selectedOption = index,
                answers = updatedAnswers
            )
        }
    }

    fun nextQuestion() {
        val state = _uiState.value
        if (state.currentIndex < state.questions.size - 1) {
            _uiState.update {
                it.copy(
                    currentIndex = it.currentIndex + 1,
                    selectedOption = it.answers[it.currentIndex + 1],
                    timeLeft = 30
                )
            }
            startTimer()
        } else {
            timerJob?.cancel()
            _uiState.update { it.copy(isFinished = true) }
        }
    }

    fun previousQuestion() {
        val state = _uiState.value
        if (state.currentIndex > 0) {
            _uiState.update {
                it.copy(
                    currentIndex = it.currentIndex - 1,
                    selectedOption = it.answers[it.currentIndex - 1],
                    timeLeft = 30
                )
            }
            startTimer()
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            while (_uiState.value.timeLeft > 0) {
                delay(1000)
                _uiState.update { it.copy(timeLeft = it.timeLeft - 1) }
            }
            nextQuestion()
        }
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}