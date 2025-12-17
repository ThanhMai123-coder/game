package com.example.testthu.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testthu.data.Comment
import kotlinx.coroutines.launch

class CommentViewModel(private val repository: CommentRepository) : ViewModel() {

    private val _comments = MutableLiveData<List<Comment>>()
    val comments: LiveData<List<Comment>> get() = _comments

    init {
        loadComments() // Tự động tải bình luận khi ViewModel khởi tạo
    }

    fun insert(comment: Comment) {
        viewModelScope.launch {
            repository.insert(comment)
            loadComments() // Tải lại bình luận sau khi thêm
        }
    }

    fun delete(comment: Comment) {
        viewModelScope.launch {
            repository.delete(comment)
            loadComments() // Tải lại bình luận sau khi xóa
        }
    }

    fun loadComments() {
        viewModelScope.launch {
            _comments.value = repository.getAllComments() // Lấy tất cả bình luận
        }
    }
}