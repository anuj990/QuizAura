package com.example.quizaura.data

import retrofit2.http.GET
import retrofit2.http.Query
data class TriviaResponse(
    val response_code : Int,
    val results: List<TriviaQuestion>)
data class TriviaQuestion(
    val question: String,
    val correct_answer : String,
    val incorrect_answers: List<String>)
interface TriviaApi {
    @GET("api.php")
    suspend fun getQuestions(
        @Query("amount") amount: Int = 10,
        @Query("category") category: Int = 18,
        @Query("type") type: String = "multiple"
    ): TriviaResponse
}