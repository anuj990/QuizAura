package com.example.quizaura.data

import android.text.Html
import com.example.quizaura.data.local.toEntity
import com.example.quizaura.data.local.toQuestion
import com.example.quizaura.data.local.QuestionDao

class QuizRepository(
    private val dao: QuestionDao,
    private val api: TriviaApi
) {
    suspend fun getQuestions(): Result<List<Question>> {
        return try {
            dao.clearQuestions()
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

                    question = Html.fromHtml(
                        trivia.question,
                        Html.FROM_HTML_MODE_LEGACY
                    ).toString(),

                    options = allOptions.map {

                        Html.fromHtml(
                            it,
                            Html.FROM_HTML_MODE_LEGACY
                        ).toString()
                    },

                    correctIndex = correctIndex,

                    explanation = Html.fromHtml(
                        "Correct answer: ${trivia.correct_answer}",
                        Html.FROM_HTML_MODE_LEGACY
                    ).toString()
                )
            }

            dao.insertQuestions(questions.map { it.toEntity() })
            Result.success(questions)

        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}