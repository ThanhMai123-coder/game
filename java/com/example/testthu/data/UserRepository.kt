package com.example.testthu.data

import androidx.lifecycle.LiveData
import androidx.room.Query

class UserRepository(private val userDao: UserDao) {
    val allUsers: LiveData<List<User>> = userDao.getAllUsers() // Use userDao instance

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun update(user: User) {
        userDao.update(user)
    }

    suspend fun delete(user: User) {
        userDao.delete(user)
    }

    @Query("SELECT * FROM users WHERE username = :username LIMIT 1")
    suspend fun getUserByUsername(username: String): com.example.testthu.data.User? {
        return TODO("Provide the return value")
    }

    @Query("DELETE FROM users WHERE id = :userId")
    suspend fun deleteById(userId: Int) {
    }
}