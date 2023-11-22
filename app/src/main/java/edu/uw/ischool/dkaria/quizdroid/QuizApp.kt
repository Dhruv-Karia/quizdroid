package edu.uw.ischool.dkaria.quizdroid

import android.app.Application
import android.util.Log

class QuizApp : Application() {
    val topicRepository: TopicRepository by lazy {
        val repo = InMemoryTopicRepository(this)
        repo.getAllTopics()
        repo
    }

    override fun onCreate() {
        super.onCreate()

        Log.d("QuizApp", "QuizApp is loaded and running.")
        val repo = topicRepository
    }
}

