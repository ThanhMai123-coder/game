package com.example.testthu.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import com.example.testthu.data.Comment
import com.example.testthu.R

class CommentAdapter(
    context: Context,
    private var comments: MutableList<Comment>,
    private val onDeleteClick: (Comment) -> Unit
) : ArrayAdapter<Comment>(context, 0, comments) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val comment = getItem(position)!!

        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.comment_item, parent, false)

        // Lấy các thành phần từ layout
        val userNameTextView: TextView = view.findViewById(R.id.textViewUserName)
        val contentTextView: TextView = view.findViewById(R.id.textViewContent)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingBar)
        val deleteButton: Button = view.findViewById(R.id.buttonDelete)

        // Cập nhật dữ liệu cho các thành phần
        userNameTextView.text = comment.userName
        contentTextView.text = comment.content
        ratingBar.rating = comment.rating

        // Xử lý sự kiện click cho nút xóa
        deleteButton.setOnClickListener {
            onDeleteClick(comment)
        }

        return view
    }

    fun updateComments(newComments: List<Comment>) {
        comments.clear()
        comments.addAll(newComments)
        notifyDataSetChanged()
    }
}