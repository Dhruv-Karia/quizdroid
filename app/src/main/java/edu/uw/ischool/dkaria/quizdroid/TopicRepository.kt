package edu.uw.ischool.dkaria.quizdroid

import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.preference.PreferenceManager
import okhttp3.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context)
        val url = sharedPreferences.getString("url", "https://dhruv-karia.github.io/JSONhost/second.json")

        val request = Request.Builder()
            .url(url!!)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    val jsonData = response.body!!.string()
                    val gson = Gson()
                    val topicsWrapper = gson.fromJson(jsonData, TopicsWrapper::class.java)
                    topics = topicsWrapper.topics
                }
            }
        })
    }

    override fun getAllTopics(): List<Topic> {
        return topics
    }

    override fun getTopicByName(topicName: String): Topic? {
        return topics.find { it.title == topicName }
    }
}
