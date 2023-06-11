package com.example.classwork10

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
//import com.example.classwork10.model.Post
import com.example.classwork10.model.Task

class MainActivity : AppCompatActivity() {

    private val blogService: BlogService = BlogService()
    private val taskService: TaskService = TaskService()

    companion object {
        const val TAG = "MainActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val listPostView: ListView = findViewById(R.id.postListView)
        val listTaskView: ListView = findViewById(R.id.postListView)
//        Log.i(TAG, "onCreate: Thread ${Thread.currentThread().name}") // UI
//        blogService.getAllPosts(object : BlogService.PostLoadCallback {
        taskService.getAllTasks(object : TaskService.TaskLoadCallback {
            // worker Thread
//            override fun postLoaded(posts: Array<Post>) {
//            override fun postLoaded(posts: Array<Task>) {
            override fun taskLoaded(tasks: Array<Task>) {
//                Log.i(TAG, "postLoaded Thread: ${Thread.currentThread().name}") // worker
//                listPostView.post {
//                    Log.i(TAG, "post Thread: ${Thread.currentThread().name}") // UI
//                    listPostView.adapter = PostAdapter(applicationContext, posts)
//                }
                listTaskView.post {
                    listTaskView.adapter = TaskAdapter(applicationContext, tasks)
                }
            }
        })
    }

//    class PostAdapter(context: Context, posts: Array<Post>) :
    class TaskAdapter(context: Context, tasks: Array<Task>) :
//        ArrayAdapter<Post>(context, 0, posts) {
        ArrayAdapter<Task>(context, 0, tasks) {
        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val view = LayoutInflater.from(context).inflate(R.layout.post_item, parent, false)
            val post = getItem(position)
//            view.findViewById<TextView>(R.id.textViewPostId).text = "${post?.id}"
            view.findViewById<TextView>(R.id.taskId).text = "${post?.id}"
//            view.findViewById<TextView>(R.id.textViewPostTitle).text = "${post?.title}"
            view.findViewById<TextView>(R.id.taskTitle).text = "${post?.title}"
            view.findViewById<TextView>(R.id.taskCompleted).text = "${post?.completed}"
            return view
        }
    }
}