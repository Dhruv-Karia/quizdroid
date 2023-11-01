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
            val intent = Intent(this, TopicOverviewActivity::class.java)
            intent.putExtra("TOPIC_NAME", "Math")
            startActivity(intent)
        }

        val btnPhysics = findViewById<Button>(R.id.btnPhysics)
        btnPhysics.setOnClickListener {
            val intent = Intent(this, TopicOverviewActivity::class.java)
            intent.putExtra("TOPIC_NAME", "Physics")
            startActivity(intent)
        }

        val btnMarvel = findViewById<Button>(R.id.btnMarvel)
        btnMarvel.setOnClickListener {
            val intent = Intent(this, TopicOverviewActivity::class.java)
            intent.putExtra("TOPIC_NAME", "Marvel Super Heroes")
            startActivity(intent)
        }

        val btnHistory = findViewById<Button>(R.id.btnHistory)
        btnHistory.setOnClickListener {
            val intent = Intent(this, TopicOverviewActivity::class.java)
            intent.putExtra("TOPIC_NAME", "History")
            startActivity(intent)
        }

        val btnScience = findViewById<Button>(R.id.btnScience)
        btnScience.setOnClickListener {
            val intent = Intent(this, TopicOverviewActivity::class.java)
            intent.putExtra("TOPIC_NAME", "Science")
            startActivity(intent)
        }

    }
}
