package edu.uw.ischool.dkaria.quizdroid

data class Question(
    val questionText: String,
    val answers: List<String>,
    val correctAnswerIndex: Int
)

data class Quiz(
    val question: Question
)

data class Topic(
    val title: String,
    val shortDescription: String,
    val longDescription: String,
    val questions: List<Quiz>
)
