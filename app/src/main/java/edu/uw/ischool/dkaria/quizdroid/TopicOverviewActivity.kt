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
        val topic = topics.find { it.topicName == topicName }

        val tvTopicDescription = findViewById<TextView>(R.id.tvTopicDescription)
        val tvTotalQuestions = findViewById<TextView>(R.id.tvTotalQuestions)
        val btnBegin = findViewById<Button>(R.id.btnBegin)

        // Set the topic description and total number of questions
        tvTopicDescription.text = topic?.description
        tvTotalQuestions.text = "Total Questions: ${topic?.questions?.size ?: 0}"

        btnBegin.setOnClickListener {
            val intent = Intent(this, QuestionActivity::class.java)
            intent.putExtra("TOPIC_NAME", topicName)
            startActivity(intent)
        }
    }
}
