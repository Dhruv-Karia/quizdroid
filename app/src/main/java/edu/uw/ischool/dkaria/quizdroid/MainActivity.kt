package edu.uw.ischool.dkaria.quizdroid

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnMath = findViewById<Button>(R.id.btnMath)
        btnMath.setOnClickListener {
            val topic = (application as QuizApp).topicRepository.getTopicByName("Math")
            if (topic != null) {
                val intent = Intent(this, TopicOverviewActivity::class.java)
                intent.putExtra("TOPIC_NAME", topic.title)
                startActivity(intent)
            }
        }

        val btnPhysics = findViewById<Button>(R.id.btnPhysics)
        btnPhysics.setOnClickListener {
            val topic = (application as QuizApp).topicRepository.getTopicByName("Physics")
            if (topic != null) {
                val intent = Intent(this, TopicOverviewActivity::class.java)
                intent.putExtra("TOPIC_NAME", topic.title)
                startActivity(intent)
            }
        }

        val btnMarvel = findViewById<Button>(R.id.btnMarvel)
        btnMarvel.setOnClickListener {
            val topic = (application as QuizApp).topicRepository.getTopicByName("Marvel Super Heroes")
            if (topic != null) {
                val intent = Intent(this, TopicOverviewActivity::class.java)
                intent.putExtra("TOPIC_NAME", topic.title)
                startActivity(intent)
            }
        }

        val btnHistory = findViewById<Button>(R.id.btnHistory)
        btnHistory.setOnClickListener {
            val topic = (application as QuizApp).topicRepository.getTopicByName("History")
            if (topic != null) {
                val intent = Intent(this, TopicOverviewActivity::class.java)
                intent.putExtra("TOPIC_NAME", topic.title)
                startActivity(intent)
            }
        }

        val btnScience = findViewById<Button>(R.id.btnScience)
        btnScience.setOnClickListener {
            val topic = (application as QuizApp).topicRepository.getTopicByName("Science")
            if (topic != null) {
                val intent = Intent(this, TopicOverviewActivity::class.java)
                intent.putExtra("TOPIC_NAME", topic.title)
                startActivity(intent)
            }
        }
    }
}
