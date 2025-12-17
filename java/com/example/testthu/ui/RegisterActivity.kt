package com.example.testthu.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.testthu.R
import com.example.testthu.data.AppDatabase
import com.example.testthu.data.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.security.MessageDigest

class RegisterActivity : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var confirmPasswordEditText: EditText
    private lateinit var registerButton: Button
    private lateinit var loginLink: TextView
    private lateinit var database: AppDatabase

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        // Initialize views
        usernameEditText = findViewById(R.id.username)
        passwordEditText = findViewById(R.id.password)
        confirmPasswordEditText = findViewById(R.id.confirmPassword)
        registerButton = findViewById(R.id.registerButton)
        loginLink = findViewById(R.id.loginLink)

        // Initialize database
        database = AppDatabase.getDatabase(this)

        registerButton.setOnClickListener { register() }
        loginLink.setOnClickListener { goToLogin() }
    }

    private fun register() {
        val username = usernameEditText.text.toString()
        val password = passwordEditText.text.toString()
        val confirmPassword = confirmPasswordEditText.text.toString()

        // Validate input fields
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, R.string.fill_all_fields, Toast.LENGTH_SHORT).show()
            return
        }

        if (password != confirmPassword) {
            Toast.makeText(this, R.string.passwords_do_not_match, Toast.LENGTH_SHORT).show()
            return
        }
        val hashedPassword = hashPassword(password)
        // Save user data to the database
        val user = User(
            username = username,
            password = hashedPassword,
            id = 0, // Room will auto-generate an ID
            role = "User" // Default role
        )

        lifecycleScope.launch(Dispatchers.IO) {
            val existingUser = database.userDao().getUserByUsername(username)
            if (existingUser == null) {
                database.userDao().insert(user)

                // Save to SharedPreferences
                val sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE)
                sharedPreferences.edit().apply {
                    putString("USERNAME", username)
                    putString("PASSWORD", hashedPassword)
                    apply() // Commit changes
                }

                runOnUiThread {
                    Toast.makeText(this@RegisterActivity, R.string.registration_successful, Toast.LENGTH_SHORT).show()

                    // Directly start SignInActivity
                    val intent = Intent(this@RegisterActivity, SignInActivity::class.java)
                    startActivity(intent)
                    finish() // Close the registration activity
                }
            } else {
                runOnUiThread {
                    Toast.makeText(this@RegisterActivity, R.string.username_exists, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun hashPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashedBytes = digest.digest(password.toByteArray())
        return hashedBytes.joinToString("") { String.format("%02x", it) }
    }
    private fun goToLogin() {
        val intent = Intent(this, SignInActivity::class.java)
        startActivity(intent)
        // No need to call finish() here if you want to return to the registration screen
    }
}