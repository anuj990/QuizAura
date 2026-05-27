package com.example.quizaura.presentation.result

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(modifier: Modifier = Modifier, score: Int, total: Int, onPlayAgain: () -> Unit) {
    val percentage = if(total>0) (score*100)/total else 0
    val wrong = total-score
    Column(
        modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Quiz Completed", style = MaterialTheme.typography.headlineMedium)
        Text(
            text = "$score / $total",
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(modifier.height(32.dp))
        Text("Correct $score")
        Text("Wrong: $wrong")
        Text("Percentage $percentage%")

        Spacer(modifier.height(12.dp))
        Button(onClick = onPlayAgain,modifier.fillMaxWidth()) {
            Text("Play Again")
        }

    }
}