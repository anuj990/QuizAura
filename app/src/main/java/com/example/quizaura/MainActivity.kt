package com.example.quizaura

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.example.quizaura.navigation.NavGraph
import com.example.quizaura.ui.theme.QuizAuraTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            QuizAuraTheme {

                val navController =
                    rememberNavController()

                NavGraph(
                    navController = navController
                )
            }
        }
    }
}