package com.example.classwork10

import android.util.Log
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import com.example.classwork10.model.Post
import java.io.IOException


class BlogService {
    private val client: OkHttpClient = OkHttpClient()
    private val gson: Gson = GsonBuilder().create()

    companion object {
        private const val TAG = "BlogService"
        private const val BASE_URL: String = "https://jsonplaceholder.typicode.com"
        const val POST_URL: String = "$BASE_URL/posts"
    }

    interface PostLoadCallback {
        fun postLoaded(posts: Array<Post>)
    }

    fun getAllPosts(postLoadCallback: PostLoadCallback) {
        val request: Request = Request.Builder()
            .url(POST_URL)
            .build()
//        val response = client.newCall(request).execute() // block current thread
        Log.i(TAG, "getAllPosts: Thread ${Thread.currentThread().name}") // UI
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e(TAG, e.message, e)
            }

            override fun onResponse(call: Call, response: Response) {
                Log.i(TAG, "onResponse: Thread ${Thread.currentThread().name}") // worker
                response.body.let {
                    val json = it?.string()
//                    Log.i(TAG, "onResponse: json = $json")
                    val posts = gson.fromJson(json!!, Array<Post>::class.java)
                    Log.i(TAG, "onResponse: posts = $posts")
                    postLoadCallback.postLoaded(posts)
                }
            }
        })
    }
}