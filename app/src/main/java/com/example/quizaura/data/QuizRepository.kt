package com.example.quizaura.data

import android.content.Context
import com.example.quizaura.data.local.QuestionDao
import com.example.quizaura.data.local.QuestionEntity
import com.google.gson.Gson

data class QuestionListDto(val questions: List<QuestionDto>)
data class QuestionDto(
    val id: Int,
    val question: String,
    val options: List<String>,
    val correctIndex: Int,
    val explanation: String
)

class QuizRepository(
    private val context: Context,
    private val dao: QuestionDao
) {
    suspend fun getQuestions(): Result<List<Question>> {
        return try {
            val cached = dao.getQuestions()

            if (cached.isEmpty()) {
                val json = context.assets.open("questions.json")
                    .bufferedReader().use { it.readText() }

                val parsed = Gson().fromJson(json, QuestionListDto::class.java)

                val entities = parsed.questions.map { dto ->
                    QuestionEntity(
                        id = dto.id,
                        options = Gson().toJson(dto.options),
                        correctIndex = dto.correctIndex,
                        explanation = dto.explanation,
                        questions = dto.question
                    )
                }
                dao.insertQuestions(entities)
            }

            val questions = dao.getQuestions().map { entity ->
                Question(
                    id = entity.id,
                    options = Gson().fromJson(entity.options, Array<String>::class.java).toList(),
                    correctIndex = entity.correctIndex,
                    explanation = entity.explanation,
                    question = entity.questions
                )
            }

            Result.success(questions)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}