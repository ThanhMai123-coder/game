package com.example.testthu.ui

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RatingBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.testthu.data.Comment
import com.example.testthu.data.DataApp
import com.example.testthu.R

class CommentUser : AppCompatActivity() {
    private lateinit var viewModel: CommentViewModel
    private lateinit var adapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comment_user)

        val dao = DataApp.getDatabase(application).commentDao()
        val repository = CommentRepository(dao)
        val factory = CommentViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(CommentViewModel::class.java)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, mutableListOf())
        findViewById<ListView>(R.id.listViewComments).adapter = adapter

        viewModel.comments.observe(this, { comments ->
            adapter.clear()
            adapter.addAll(comments.map { "${it.userName}: ${it.content} (Rating: ${it.rating})" })
        })

        findViewById<Button>(R.id.buttonSubmit).setOnClickListener {
            val userName = findViewById<EditText>(R.id.editTextUserName).text.toString()
            val content = findViewById<EditText>(R.id.editTextComment).text.toString()
            val rating = findViewById<RatingBar>(R.id.ratingBar).rating

            if (userName.isNotEmpty() && content.isNotEmpty()) {
                val comment = Comment(userName = userName, content = content, rating = rating)
                viewModel.insert(comment)
                findViewById<EditText>(R.id.editTextUserName).text.clear()
                findViewById<EditText>(R.id.editTextComment).text.clear()
                findViewById<RatingBar>(R.id.ratingBar).rating = 0f // Reset RatingBar
            } else {
                Toast.makeText(this, "Please enter a user name and comment.", Toast.LENGTH_SHORT).show()
            }
        }
    }
}