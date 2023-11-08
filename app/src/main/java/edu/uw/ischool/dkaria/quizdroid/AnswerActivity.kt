package edu.uw.ischool.dkaria.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class AnswerActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_answer)

        val topicName = intent.getStringExtra("TOPIC_NAME")
        var questionIndex = intent.getIntExtra("QUESTION_INDEX", 0)
        val userAnswer = intent.getStringExtra("USER_ANSWER")
        var correctAnswers = intent.getIntExtra("CORRECT_ANSWERS", 0)
        var incorrectAnswers = intent.getIntExtra("INCORRECT_ANSWERS", 0)

        val topic = (application as QuizApp).topicRepository.getTopicByName(topicName!!)
        val currentTopicQuestions = topic?.questions
        if (currentTopicQuestions != null && questionIndex < currentTopicQuestions.size) {
            val question = currentTopicQuestions[questionIndex].question

            val tvCorrectAnswer = findViewById<TextView>(R.id.tvCorrectAnswer)
            val tvUserAnswer = findViewById<TextView>(R.id.tvUserAnswer)
            val tvScoreSoFar = findViewById<TextView>(R.id.tvScoreSoFar)

            tvCorrectAnswer.text = question.answers[question.correctAnswerIndex]
            tvUserAnswer.text = userAnswer

            if (userAnswer == question.answers[question.correctAnswerIndex]) {
                correctAnswers++
            } else {
                incorrectAnswers++
            }

            tvScoreSoFar.text =
                "Correct Answers: $correctAnswers\nIncorrect Answers: $incorrectAnswers"

            val btnNextOrFinish = findViewById<Button>(R.id.btnNextOrFinish)

            if (questionIndex + 1 < currentTopicQuestions.size) {
                btnNextOrFinish.text = "Next"
                btnNextOrFinish.setOnClickListener {
                    questionIndex++
                    val intent = Intent(this, QuestionActivity::class.java)
                    intent.putExtra("TOPIC_NAME", topicName)
                    intent.putExtra("QUESTION_INDEX", questionIndex)
                    intent.putExtra("CORRECT_ANSWERS", correctAnswers)
                    intent.putExtra("INCORRECT_ANSWERS", incorrectAnswers)
                    startActivity(intent)
                }
            } else {
                btnNextOrFinish.text = "Finish"
                btnNextOrFinish.setOnClickListener {
                    startActivity(Intent(this, MainActivity::class.java))
                }
            }
        }
    }
}
