package com.example.testthu.ui

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.testthu.R

class luoi9x9CaroGame : AppCompatActivity() {

    private lateinit var turnTextView: TextView
    private lateinit var gridLayout: GridLayout
    private var currentTurn = 'X'
    private val board = Array(8) { CharArray(8) { ' ' } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.luoi9x9)

        turnTextView = findViewById(R.id.turnTV)
        gridLayout = findViewById(R.id.gridLayout)

        for (i in 0 until gridLayout.childCount) {
            val button = gridLayout.getChildAt(i) as Button
            button.setOnClickListener { onCellClicked(button, i) }
        }
    }

    private fun onCellClicked(button: Button, index: Int) {
        val row = index / 8
        val col = index % 8

        if (board[row][col] == ' ') {
            board[row][col] = currentTurn
            button.text = currentTurn.toString()
            button.setTextColor(Color.BLACK) // Đảm bảo màu văn bản là đen
            if (checkForVictory(currentTurn)) {
                showResult("$currentTurn wins!")
            } else if (isBoardFull()) {
                showResult("It's a draw!")
            } else {
                currentTurn = if (currentTurn == 'X') 'O' else 'X'
                turnTextView.text = "Turn: $currentTurn"
            }
        }
    }

    private fun checkForVictory(player: Char): Boolean {
        // Kiểm tra các hàng, cột và đường chéo để xác định chiến thắng
        for (i in 0 until 8) {
            if ((0 until 8).all { board[i][it] == player } ||
                (0 until 8).all { board[it][i] == player }) {
                return true
            }
        }
        return (0 until 8).all { board[it][it] == player } ||
                (0 until 8).all { board[it][7 - it] == player }
    }

    private fun isBoardFull(): Boolean {
        return board.all { row -> row.all { it != ' ' } }
    }

    private fun showResult(message: String) {
        AlertDialog.Builder(this)
            .setTitle("Game Over")
            .setMessage(message)
            .setPositiveButton("Reset") { _, _ -> resetGame() }
            .setCancelable(false)
            .show()
    }

    private fun resetGame() {
        for (i in 0 until gridLayout.childCount) {
            val button = gridLayout.getChildAt(i) as Button
            button.text = ""
            button.setTextColor(Color.BLACK) // Đặt lại màu văn bản về đen
        }
        for (i in 0 until 8) {
            board[i].fill(' ')
        }
        currentTurn = 'X'
        turnTextView.text = "Turn: X"
    }
}