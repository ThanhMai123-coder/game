package com.example.testthu.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val username: String,
    val password: String,
    val role: String, // Changed from grade to role
    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis()
)