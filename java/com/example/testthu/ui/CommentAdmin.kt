package com.example.testthu.ui

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.testthu.data.DataApp
import com.example.testthu.data.Comment
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import com.example.testthu.R

class CommentAdmin : AppCompatActivity() {

    private lateinit var listViewAdminComments: ListView
    private lateinit var commentAdapter: CommentAdapter
    private var comments: MutableList<Comment> = mutableListOf() // Danh sách bình luận
    private lateinit var db: DataApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.comment_admin)

        listViewAdminComments = findViewById(R.id.listViewAdminComments)
        db = DataApp.getDatabase(this)

        // Lấy dữ liệu bình luận từ Room
        fetchComments()
    }

    private fun fetchComments() {
        lifecycleScope.launch {
            val commentsFromDb = withContext(Dispatchers.IO) {
                db.commentDao().getAllComments()
            }
            comments.clear()
            comments.addAll(commentsFromDb)
            commentAdapter = CommentAdapter(this@CommentAdmin, comments) { comment ->
                deleteComment(comment)
            }
            listViewAdminComments.adapter = commentAdapter // Thiết lập adapter cho ListView
        }
    }

    private fun deleteComment(comment: Comment) {
        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                // Xoá comment từ database nếu cần
                // db.commentDao().delete(comment) // Nếu có phương thức xóa
            }
            comments.remove(comment)
            commentAdapter.notifyDataSetChanged()
        }
    }
}