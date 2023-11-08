package edu.uw.ischool.dkaria.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : Application() {
    val topicRepository: TopicRepository by lazy {
        InMemoryTopicRepository()
    }

    override fun onCreate() {
        super.onCreate()
        Log.d("QuizApp", "QuizApp is loaded and running.")
    }
}
