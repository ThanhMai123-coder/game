package com.example.testthu.data


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class Comment(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val userName: String,
    val content: String,
    var rating: Float
)