package com.example.classwork10

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import com.example.classwork10.model.Task
import java.io.IOException


class TaskService {
    private val client: OkHttpClient = OkHttpClient()
    private val gson: Gson = GsonBuilder().create()

    companion object {
        private const val TAG = "TaskService"
        private const val BASE_URL: String = "https://jsonplaceholder.typicode.com"
        const val POST_URL: String = "$BASE_URL/todos"
    }

    interface TaskLoadCallback {
        fun taskLoaded(tasks: Array<Task>)
    }

    fun getAllTasks(taskLoadCallback: TaskLoadCallback) {
        val request: Request = Request.Builder()
            .url(POST_URL)
            .build()

        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, e.message, e)
            }

            override fun onResponse(call: Call, response: Response) {
                response.body.let {
                    val json = it?.string()
                    val tasks = gson.fromJson(json!!, Array<Task>::class.java)

                    taskLoadCallback.taskLoaded(tasks)
                }
            }
        })
    }
}