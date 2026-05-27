package com.example.quizaura.presentation.result

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun ResultScreen(
    score: Int, total: Int, onPlayAgain: () -> Unit
) {


    val percentage = if (total > 0) (score * 100) / total
    else 0

    val wrong = total - score

    val progress by animateFloatAsState(
        targetValue = percentage / 100f, label = ""
    )
    val progressColor = when {
        percentage >= 70 -> MaterialTheme.colorScheme.primary
        percentage >= 40 -> MaterialTheme.colorScheme.tertiary
        else -> MaterialTheme.colorScheme.error
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),

        containerColor = MaterialTheme.colorScheme.surface
    ) { padding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),

            verticalArrangement = Arrangement.Center,

            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Surface(
                shape = RoundedCornerShape(32.dp),

                color = MaterialTheme.colorScheme.primaryContainer,

                modifier = Modifier.size(96.dp)
            ) {

                Box(
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.CheckCircle,

                        contentDescription = null,

                        modifier = Modifier
                            .size(52.dp)
                            .scale(1.1f),

                        tint = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(28.dp)
            )

            Text(
                text = "Quiz Completed",

                style = MaterialTheme.typography.headlineLarge,

                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "Great effort, keep improving every day",

                style = MaterialTheme.typography.bodyMedium,

                color = MaterialTheme.colorScheme.onSurfaceVariant
            )

            Spacer(
                modifier = Modifier.height(36.dp)
            )

            Box(
                contentAlignment = Alignment.Center
            ) {

                CircularProgressIndicator(
                    progress = { progress },
                    modifier = Modifier.size(160.dp),
                    strokeWidth = 12.dp,
                    color = progressColor
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = "$percentage%",

                        style = MaterialTheme.typography.displaySmall,

                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "Score",

                        style = MaterialTheme.typography.bodyMedium,

                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(36.dp)
            )

            ElevatedCard(
                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(28.dp),

                colors = CardDefaults.elevatedCardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainerHigh
                )
            ) {

                Column(
                    modifier = Modifier.padding(24.dp),

                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {

                    ResultItem(
                        title = "Correct Answers", value = "$score"
                    )

                    ResultItem(
                        title = "Wrong Answers", value = "$wrong"
                    )

                    ResultItem(
                        title = "Total Questions", value = "$total"
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(32.dp)
            )

            Button(
                onClick = onPlayAgain,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),

                shape = RoundedCornerShape(20.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            ) {

                Text(
                    text = "Play Again",

                    style = MaterialTheme.typography.titleMedium,

                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun ResultItem(
    title: String, value: String
) {

    ElevatedCard(
        modifier = Modifier.fillMaxWidth(),

        shape = RoundedCornerShape(20.dp),

        colors = CardDefaults.elevatedCardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHighest
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = 20.dp, vertical = 18.dp
                ),

            horizontalArrangement = Arrangement.SpaceBetween,

            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = title,

                style = MaterialTheme.typography.bodyLarge
            )

            Text(
                text = value,

                style = MaterialTheme.typography.titleLarge,

                fontWeight = FontWeight.Bold,

                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}