package com.example.testthu.ui

import com.example.testthu.data.Comment
import com.example.testthu.data.CommentDao

class CommentRepository(private val commentDao: CommentDao) {

    // Hàm để thêm bình luận
    suspend fun insert(comment: Comment) {
        commentDao.insert(comment)
    }

    // Hàm để lấy tất cả bình luận
    suspend fun getAllComments(): List<Comment> {
        return commentDao.getAllComments()
    }

    // Hàm để xóa bình luận
    suspend fun delete(comment: Comment) {
        commentDao.delete(comment)
    }
}