package com.example.testthu.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.testthu.R
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class CaroActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_caro)

        val button3x3: Button = findViewById(R.id.button_3x3)
        val button6x6: Button = findViewById(R.id.button_6x6)


        button3x3.setOnClickListener { showGameMode(3) }
        button6x6.setOnClickListener { showGameMode(6) }

    }

    private fun showGameMode(gridSize: Int) {
        // Start an activity to choose the game mode (2 players or vs computer)
        val intent = Intent(this, GameModeActivity::class.java).apply {
            putExtra("GRID_SIZE", gridSize)
        }
        startActivity(intent)
    }
}