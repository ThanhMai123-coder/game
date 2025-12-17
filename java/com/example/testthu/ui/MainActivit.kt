package com.example.testthu.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.testthu.R
import androidx.appcompat.app.AppCompatActivity
import com.example.testthu.R.id.btnQuizzmath

class MainActivit : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the Caro button
        val caroButton: Button = findViewById(R.id.btnCaro)
        caroButton.setOnClickListener {
            // Show a toast for debugging
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
            // Start CaroGameActivity when the Caro button is clicked
            val intent = Intent(this, CaroActivity::class.java)
            startActivity(intent)
        }

        // Initialize other game buttons (optional)
        val QuizzMathButton: Button = findViewById(btnQuizzmath)
        QuizzMathButton.setOnClickListener {
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
            // Start CaroGameActivity when the Caro button is clicked
            val intent = Intent(this, QuizzMath::class.java)
            startActivity(intent)
        }

        val dhbcButton: Button = findViewById(R.id.btnDhbc)
        dhbcButton.setOnClickListener {
            // Start MineSweeperGameActivity (not implemented yet)
            Toast.makeText(this, "Button Clicked", Toast.LENGTH_SHORT).show()
            // Start CaroGameActivity when the Caro button is clicked
            val intent = Intent(this, dhbc::class.java)
            startActivity(intent)
        }
    }
}