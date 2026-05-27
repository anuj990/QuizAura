package com.example.quizaura.presentation.quiz

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.quizaura.data.Question
import org.koin.androidx.compose.koinViewModel

@Composable
fun QuizScreen(
    modifier: Modifier = Modifier,
    onQuizFinished: (score: Int, total: Int) -> Unit
) {
    val viewModel: QuizViewModel = koinViewModel()
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(state.isFinished) {
        if (state.isFinished) onQuizFinished(state.score, state.questions.size)
    }
    Scaffold(modifier = Modifier.fillMaxSize()) { padding ->
        if (state.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else if (state.error != null) {
            Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text(text = state.error!!, color = MaterialTheme.colorScheme.error)
            }
        } else if (state.questions.isNotEmpty()) {
            val questions = state.questions[state.currentIndex]
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Q ${state.currentIndex + 1}/${state.questions.size}",
                        style = MaterialTheme.typography.labelLarge
                    )
                    Text(
                        text = "⏱ ${state.timeLeft}s",
                        style = MaterialTheme.typography.labelLarge,
                        color = if (state.timeLeft <= 10) MaterialTheme.colorScheme.error else
                            MaterialTheme.colorScheme.onSurface
                    )
                }
                Spacer(modifier.height(24.dp))
                Text(text = questions.question, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier.height(24.dp))
                questions.options.forEachIndexed { index, option ->
                    val isSelected = state.selectedOption == index
                    Card(
                        onClick = { viewModel.selectOption(index) },
                        modifier
                            .fillMaxWidth()
                            .padding(vertical = 6.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = if (isSelected) MaterialTheme.colorScheme.primaryContainer
                            else MaterialTheme.colorScheme.surfaceVariant
                        )
                    ) {
                        Text(
                            text = option,
                            modifier.padding(16.dp),
                            style = MaterialTheme.typography.bodyLarge
                        )

                    }
                }
                Spacer(modifier.weight(1f))
                Row(modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(
                        onClick = { viewModel.previousQuestion() },
                        enabled = state.currentIndex > 0
                    ) {
                        Text("Previous")
                    }
                    Button(onClick = { viewModel.nextQuestion() }) {
                        if (state.currentIndex == state.questions.size - 1)
                            Text("Finish")
                        else Text("Next")
                    }
                }
            }
        }
    }
}
