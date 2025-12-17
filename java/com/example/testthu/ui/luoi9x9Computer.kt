package com.example.testthu.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.random.Random
import com.example.testthu.R

class luoi9x9Computer : AppCompatActivity() {

    private lateinit var turnTextView: TextView
    private lateinit var gridLayout: GridLayout
    private var currentTurn = 'X'
    private val board = Array(9) { CharArray(9) { ' ' } }

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
        val row = index / 9
        val col = index % 9

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
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                if (board[i][j] == ' ') {
                    availableMoves.add(Pair(i, j))
                }
            }
        }

        if (availableMoves.isNotEmpty()) {
            val (row, col) = availableMoves[Random.nextInt(availableMoves.size)]
            board[row][col] = currentTurn
            val button = gridLayout.getChildAt(row * 9 + col) as Button
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
        for (i in 0 until 9) {
            if ((0 until 9).all { board[i][it] == player } ||
                (0 until 9).all { board[it][i] == player }) {
                return true
            }
        }
        return (0 until 9).all { board[it][it] == player } ||
                (0 until 9).all { board[it][8 - it] == player }
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
        for (i in 0 until 9) {
            board[i].fill(' ')
        }
        currentTurn = 'X'
        turnTextView.text = "Turn: X"
    }
}