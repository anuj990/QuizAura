package com.example.quizaura.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.quizaura.data.Question
import com.google.gson.Gson

@Entity(tableName = "questions")
data class QuestionEntity(
    @PrimaryKey val id:Int,
    val question :String,
    val options : String,
    val correctIndex : Int,
    val explanation : String
)
fun QuestionEntity.toQuestion() = Question(
    id = id,
    question = question,
    options = Gson().fromJson(options, Array<String>::class.java).toList(),
    correctIndex = correctIndex,
    explanation = explanation
)

fun Question.toEntity() = QuestionEntity(
    id = id,
    question = question,
    options = Gson().toJson(options),
    correctIndex = correctIndex,
    explanation = explanation
)