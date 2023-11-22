package edu.uw.ischool.dkaria.quizdroid

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.os.Handler
import android.os.Looper
import android.provider.Settings
import android.widget.Toast
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import okhttp3.*
import java.io.File
import java.io.IOException

interface TopicRepository {
    fun getAllTopics(): List<Topic>
    fun getTopicByName(topicName: String): Topic?
}

class InMemoryTopicRepository(private val context: Context) : TopicRepository {
    private val client = OkHttpClient()
    private var topics: List<Topic> = listOf()

    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            fetchTopics()
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val interval = sharedPreferences.getString("interval", "60")?.toLong() ?: 60
            handler.postDelayed(this, interval * 60 * 1000) // convert minutes to milliseconds
        }
    }

    init {
        runnable.run()
    }

    private fun fetchTopics() {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnectedOrConnecting) {
            val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
            val url = sharedPreferences.getString("url", "https://dhruv-karia.github.io/JSONhost/second.json")

            val request = Request.Builder()
                .url(url!!)
                .build()

            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    e.printStackTrace()
                    handler.post {
                        Toast.makeText(context, "Download failed. Do you want to retry or quit the application and try again later?", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    response.use {
                        if (!response.isSuccessful)  {
                            handler.post {
                                Toast.makeText(context, "Download failed. Do you want to retry or quit the application and try again later?", Toast.LENGTH_LONG).show()
                            }
                            throw IOException("Unexpected code $response")
                        }

                        val jsonData = response.body!!.string()
                        val gson = Gson()
                        val topicsWrapper = gson.fromJson(jsonData, TopicsWrapper::class.java)
                        topics = topicsWrapper.topics

                        // Save to local file
                        val file = File(context.filesDir, "questions.json")
                        file.writeText(jsonData)

                        handler.post {
                            Toast.makeText(context, "Download succeeded", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            })
        } else {
            handler.post {
                Toast.makeText(context, "No internet connection", Toast.LENGTH_LONG).show()

                if (Settings.Global.getInt(context.contentResolver, Settings.Global.AIRPLANE_MODE_ON, 0) != 0) {
                    val intent = Intent(Settings.ACTION_AIRPLANE_MODE_SETTINGS)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                    context.startActivity(intent)
                }
            }

        }
    }

    override fun getAllTopics(): List<Topic> {
        return topics
    }

    override fun getTopicByName(topicName: String): Topic? {
        return topics.find { it.title == topicName }
    }
}
