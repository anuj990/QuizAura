package com.example.quizaura.data

import android.content.Context
import com.example.quizaura.data.local.QuestionDao
import com.example.quizaura.data.local.QuestionEntity
import com.google.firebase.auth.FirebaseAuth
import com.google.gson.Gson
import kotlinx.coroutines.tasks.await

class AuthService{
    private val auth  = FirebaseAuth.getInstance()
    suspend fun signup(email: String,password: String): Result<Unit>{
       return try {
           auth.createUserWithEmailAndPassword(email,password).await()
           Result.success(Unit)
       }catch (e: Exception){
           Result.failure(e)
       }
    }
    suspend fun login(email: String,password: String): Result<Unit>{
        return try {
            auth.signInWithEmailAndPassword(email,password).await()
            Result.success(Unit)
        }
        catch (e: Exception){
            Result.failure(e)
        }
    }
     fun logout(){
        auth.signOut()
    }
    fun isLoggedIn(): Boolean{
        return auth.currentUser != null
    }
}

