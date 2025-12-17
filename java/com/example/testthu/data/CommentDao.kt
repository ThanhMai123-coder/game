package com.example.testthu.data


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface CommentDao {
    @Insert
    suspend fun insert(comment: com.example.testthu.data.Comment)

    @Query("SELECT * FROM comments")
    suspend fun getAllComments(): List<com.example.testthu.data.Comment>

    @Delete
    suspend fun delete(comment: com.example.testthu.data.Comment)
}