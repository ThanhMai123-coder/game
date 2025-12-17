package com.example.testthu.ui

import android.os.Bundle
import com.example.testthu.R
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random

class luoi6x6Computer : AppCompatActivity() {

    private lateinit var turnTextView: TextView
    private lateinit var gridLayout: GridLayout
    private var currentTurn = 'X'
    private val board = Array(6) { CharArray(6) { ' ' } }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.luoi6x6) // You can reuse the same layout

        turnTextView = findViewById(R.id.turnTV)
        gridLayout = findViewById(R.id.gridLayout)

        for (i in 0 until gridLayout.childCount) {
            val button = gridLayout.getChildAt(i) as Button
            button.setOnClickListener { onCellClicked(button, i) }
        }
    }

    private fun onCellClicked(button: Button, index: Int) {
        val row = index / 6
        val col = index % 6

        if (board[row][col] == ' ') {
            board[row][col] = currentTurn
            button.text = currentTurn.toString()
            if (checkForVictory(currentTurn)) {
                showResult("$currentTurn wins!")
            } else if (isBoardFull()) {
                showResult("It's a draw!")
            } else {
                currentTurn = 'O'
                turnTextView.text = "Turn: $currentTurn"
                computerMove()
            }
        }
    }

    private fun computerMove() {
        val availableMoves = mutableListOf<Pair<Int, Int>>()
        for (i in 0 until 6) {
            for (j in 0 until 6) {
                if (board[i][j] == ' ') {
                    availableMoves.add(Pair(i, j))
                }
            }
        }

        if (availableMoves.isNotEmpty()) {
            val (row, col) = availableMoves[Random.nextInt(availableMoves.size)]
            board[row][col] = currentTurn
            val button = gridLayout.getChildAt(row * 6 + col) as Button
            button.text = currentTurn.toString()

            if (checkForVictory(currentTurn)) {
                showResult("$currentTurn wins!")
            } else if (isBoardFull()) {
                showResult("It's a draw!")
            } else {
                currentTurn = 'X'
                turnTextView.text = "Turn: $currentTurn"
            }
        }
    }

    private fun checkForVictory(player: Char): Boolean {
        for (i in 0 until 6) {
            if ((0 until 6).all { board[i][it] == player } ||
                (0 until 6).all { board[it][i] == player }) {
                return true
            }
        }
        return (0 until 6).all { board[it][it] == player } ||
                (0 until 6).all { board[it][5 - it] == player }
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
        }
        for (i in 0 until 6) {
            board[i].fill(' ')
        }
        currentTurn = 'X'
        turnTextView.text = "Turn: X"
    }
}