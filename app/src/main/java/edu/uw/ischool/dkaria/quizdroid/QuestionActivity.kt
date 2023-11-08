package edu.uw.ischool.dkaria.quizdroid

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.ComponentActivity

class QuestionActivity : ComponentActivity() {
    private var currentQuestionIndex = 0
    private var correctAnswers = 0
    private var incorrectAnswers = 0
    private lateinit var topicName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question)

        currentQuestionIndex = intent.getIntExtra("QUESTION_INDEX", 0)
        topicName = intent.getStringExtra("TOPIC_NAME") ?: ""
        val topic = (application as QuizApp).topicRepository.getTopicByName(topicName)
        val questions = topic?.questions

        correctAnswers = intent.getIntExtra("CORRECT_ANSWERS", 0)
        incorrectAnswers = intent.getIntExtra("INCORRECT_ANSWERS", 0)

        val tvQuestion = findViewById<TextView>(R.id.tvQuestion)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)

        if (questions != null && currentQuestionIndex < questions.size) {
            val currentQuestion = questions[currentQuestionIndex].question

            tvQuestion.text = currentQuestion.questionText

            currentQuestion.answers.forEachIndexed { index, answer ->
                val radioButton = RadioButton(this)
                radioButton.text = answer
                radioGroup.addView(radioButton)
                if (index == currentQuestion.correctAnswerIndex) {
                    radioButton.tag = "Correct"
                }
            }

            radioGroup.setOnCheckedChangeListener { group, checkedId ->
                btnSubmit.visibility = View.VISIBLE
            }

            btnSubmit.setOnClickListener {
                val selectedRadioButton = radioGroup.findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
                val userAnswer = selectedRadioButton.text.toString()
                val intent = Intent(this, AnswerActivity::class.java)
                intent.putExtra("TOPIC_NAME", topicName)
                intent.putExtra("QUESTION_INDEX", currentQuestionIndex)
                intent.putExtra("USER_ANSWER", userAnswer)
                intent.putExtra("CORRECT_ANSWERS", correctAnswers)
                intent.putExtra("INCORRECT_ANSWERS", incorrectAnswers)
                startActivity(intent)
            }
        }
    }
}
