package com.example.quizaura.data

import com.example.quizaura.data.local.toEntity
import com.example.quizaura.data.local.toQuestion
import com.example.quizaura.data.local.QuestionDao

class QuizRepository(
    private val dao: QuestionDao,
    private val api: TriviaApi
) {
    suspend fun getQuestions(): Result<List<Question>> {
        return try {
            val cached = dao.getQuestions()
            if (cached.isNotEmpty()) {
                return Result.success(cached.map { it.toQuestion() })
            }

            val response = api.getQuestions()
            val questions = response.results.mapIndexed { index, trivia ->
                val allOptions = (trivia.incorrect_answers + trivia.correct_answer).shuffled()
                val correctIndex = allOptions.indexOf(trivia.correct_answer)
                Question(
                    id = index,
                    question = trivia.question,
                    options = allOptions,
                    correctIndex = correctIndex,
                    explanation = "Correct answer: ${trivia.correct_answer}"
                )
            }

            dao.insertQuestions(questions.map { it.toEntity() })
            Result.success(questions)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}