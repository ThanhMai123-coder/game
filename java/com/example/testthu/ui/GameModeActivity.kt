package com.example.testthu.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.testthu.R

class GameModeActivity : AppCompatActivity() {

    private var gridSize: Int = 2

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game_mode)

        // Lấy kích thước lưới từ Intent
        gridSize = intent.getIntExtra("GRID_SIZE", 2)

        // Hiển thị kích thước lưới
        val gridSizeText: TextView = findViewById(R.id.grid_size_text)
        gridSizeText.text = "$gridSize x $gridSize Grid"

        // Khởi tạo các nút
        val buttonTwoPlayers: Button = findViewById(R.id.button_two_players)
        val buttonVsComputer: Button = findViewById(R.id.button_vs_computer)

        // Thiết lập sự kiện click cho các nút
        buttonTwoPlayers.setOnClickListener { startGame(true) }
        buttonVsComputer.setOnClickListener { startGame(false) }
    }

    private fun startGame(isTwoPlayers: Boolean) {
        val intent = if (isTwoPlayers) {
            Intent(this, when (gridSize) {
                3 -> CaroGameActivity::class.java
                6 -> luoi6x6CaroGame::class.java
                else -> CaroGameActivity::class.java // Default case
            })
        } else {
            Intent(this, when (gridSize) {
                3 -> CaroComputerActivity::class.java
                6 -> luoi6x6Computer::class.java

                else -> CaroComputerActivity::class.java // Default case
            })
        }
        startActivity(intent)
    }
}