package com.example.quizaura.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey val id:Int,
    val questions :String,
    val options : String,
    val correctIndex : Int,
    val explanation : String
)