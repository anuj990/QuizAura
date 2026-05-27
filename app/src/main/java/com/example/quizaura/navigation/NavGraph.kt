package com.example.quizaura.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.quizaura.presentation.auth.LoginScreen
import com.example.quizaura.presentation.auth.SignupScreen
import com.example.quizaura.presentation.quiz.QuizScreen

object Routes {
    const val LOGIN = "login"
    const val SIGNUP = "signup"
    const val QUIZ = "quiz"
    const val RESULT = "result/{score}/{total}"
    fun result(score: Int, total: Int) = "result/$score/$total"
}

@Composable
fun NavGraph(modifier: Modifier = Modifier, navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigate(Routes.QUIZ) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onNavigateToSignup = {
                    navController.navigate(Routes.SIGNUP)
                }
            )
        }
        composable(Routes.SIGNUP) {
            SignupScreen(
                onSignupSuccess = {
                    navController.navigate(Routes.QUIZ) {
                        popUpTo(Routes.LOGIN) { inclusive = true }
                    }
                },
                onNavigateToLogin = {
                    navController.popBackStack()
                }
            )
        }
        composable(Routes.QUIZ) {
            QuizScreen(
                onQuizFinished = { score, total ->
                    navController.navigate(Routes.result(score, total)) {
                        popUpTo(Routes.QUIZ) { inclusive = true }
                    }
                }
            )
        }
        composable(
            route = Routes.RESULT,
            arguments = listOf(
                navArgument("score") { type = NavType.IntType },
                navArgument("total") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val score = backStackEntry.arguments?.getInt("score") ?: 0
            val total = backStackEntry.arguments?.getInt("total") ?: 0
            Text("Result Screen - Score: $score / $total")
        }
    }
}