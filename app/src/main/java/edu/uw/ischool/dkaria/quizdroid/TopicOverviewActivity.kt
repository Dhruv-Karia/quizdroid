package edu.uw.ischool.dkaria.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity
class TopicOverviewActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_topic_overview)

        val topicName = intent.getStringExtra("TOPIC_NAME")
        val topic = topicName?.let { (application as QuizApp).topicRepository.getTopicByName(it) }

        val tvTopicDescription = findViewById<TextView>(R.id.tvTopicDescription)
        val tvTotalQuestions = findViewById<TextView>(R.id.tvTotalQuestions)
        val btnBegin = findViewById<Button>(R.id.btnBegin)

        if (topic != null) {
            tvTopicDescription.text = topic.shortDescription + "\n" + topic.longDescription
            tvTotalQuestions.text = "Total Questions: ${topic.questions.size}"
        }
        btnBegin.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            intent.putExtra("TOPIC_NAME", topicName)
            startActivity(intent)
        }
    }
}
