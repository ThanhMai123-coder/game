package com.example.testthu.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testthu.R

class RoleSelectionActivity : AppCompatActivity() {

    private lateinit var roleRadioGroup: RadioGroup
    private lateinit var selectButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_role_selection)

        // Initialize views
        roleRadioGroup = findViewById(R.id.roleRadioGroup)
        selectButton = findViewById(R.id.selectButton)

        // Set click listener for the button
        selectButton.setOnClickListener { navigateBasedOnRole() }
    }

    private fun navigateBasedOnRole() {
        val selectedRoleId = roleRadioGroup.checkedRadioButtonId
        val role = when (selectedRoleId) {
            R.id.radioAdmin -> "Admin"
            R.id.radioUser -> "User"
            else -> {
                Toast.makeText(this, "Please select a role", Toast.LENGTH_SHORT).show()
                return
            }
        }

        // Navigate to the appropriate activity based on the selected role
        val intent = when (role) {
            "Admin" -> Intent(this, LoginActivity::class.java)
            "User" -> Intent(this, SignInActivity::class.java)
            else -> null
        }

        intent?.let {
            startActivity(it)
            finish() // Close the current activity
        }
    }
}