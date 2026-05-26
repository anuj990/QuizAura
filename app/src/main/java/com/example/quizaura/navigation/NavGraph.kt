package com.example.quizaura.navigation

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.quizaura.presentation.auth.LoginScreen
import com.example.quizaura.presentation.auth.SignupScreen

object Routes{
    const val  LOGIN  = "login"
    const val SIGNUP = "signup"
    const val QUIZ = "quiz"
    const val RESULT = "result"
    
}

@Composable
fun NavGraph(modifier: Modifier = Modifier,navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ){
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
            Text("Quiz Screen")
        }
        composable(Routes.RESULT) {
            Text("Result Screen")
        }
    }

}