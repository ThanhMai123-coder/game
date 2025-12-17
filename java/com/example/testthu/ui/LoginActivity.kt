package com.example.testthu.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.testthu.R
import com.example.testthu.UserManagerActivity
import com.example.testthu.data.AppDatabase
import com.example.testthu.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button
    private lateinit var loginStatus: TextView
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        loginButton = findViewById(R.id.loginButton)
        loginStatus = findViewById(R.id.loginStatus)

        // Initialize the database
        database = AppDatabase.getDatabase(this)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            // Check hardcoded credentials first
            if (username == "K" && password == "123123") {
                loginStatus.text = "Login successful!"

                // Navigate to AdminActivity
                val intent = Intent(this, AdminActivity::class.java)
                startActivity(intent)
                finish() // Close LoginActivity
            } else {
                // Check credentials in the database
                verifyUser(username, password)
            }
        }
    }

    private fun verifyUser(username: String, password: String) {
        lifecycleScope.launch(Dispatchers.IO) {
            val user: User? = database.userDao().getUserByUsername(username)

            runOnUiThread {
                if (user != null && user.password == password) {
                    loginStatus.text = "Login successful!"
                    // Always navigate to AdminActivity
                    val intent = Intent(this@LoginActivity, AdminActivity::class.java)
                    startActivity(intent)
                    finish() // Close LoginActivity
                } else {
                    loginStatus.text = "Invalid username or password."
                }
            }
        }
    }
}