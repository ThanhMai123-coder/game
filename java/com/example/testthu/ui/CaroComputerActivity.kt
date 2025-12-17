package com.example.testthu.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.testthu.R
import com.example.testthu.databinding.ActivityCaroComputerBinding

class CaroComputerActivity : AppCompatActivity() {

    enum class Turn {
        NOUGHT,
        CROSS
    }

    private var currentTurn = Turn.NOUGHT
    private lateinit var binding: ActivityCaroComputerBinding
    private val boardList = mutableListOf<Button>()

    private var crossesScore = 0
    private var noughtsScore = 0

    companion object {
        const val NOUGHT = "O"
        const val CROSS = "X"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCaroComputerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBoard()
    }

    private fun initBoard() {
        boardList.addAll(
            listOf(
                binding.a1, binding.a2, binding.a3,
                binding.b1, binding.b2, binding.b3,
                binding.c1, binding.c2, binding.c3
            )
        )

        for (button in boardList) {
            button.setOnClickListener { boardTapped(it) }
        }
    }

    fun boardTapped(view: View) {
        if (view is Button) {
            addToBoard(view)

            if (checkForVictory(NOUGHT)) {
                noughtsScore++
                result("You Win!")
            } else if (fullBoard()) {
                result("Draw")
            } else {
                computerMove() // Computer makes a move
            }
        }
    }

    private fun computerMove() {
        val bestMove = findBestMove()
        if (bestMove != null) {
            addToBoard(bestMove)

            if (checkForVictory(CROSS)) {
                crossesScore++
                result("Computer Wins!")
            } else if (fullBoard()) {
                result("Draw")
            }
        }
    }

    private fun findBestMove(): Button? {
        var bestScore = Int.MIN_VALUE
        var bestMove: Button? = null

        for (button in boardList) {
            if (button.text.isEmpty()) {
                // Try placing CROSS
                button.text = CROSS
                val score = minimax(false, Int.MIN_VALUE, Int.MAX_VALUE)
                button.text = "" // Undo move

                if (score > bestScore) {
                    bestScore = score
                    bestMove = button
                }
            }
        }
        return bestMove
    }

    private fun minimax(isMaximizing: Boolean, alpha: Int, beta: Int): Int {
        // Check terminal states
        if (checkForVictory(CROSS)) return 10
        if (checkForVictory(NOUGHT)) return -10
        if (fullBoard()) return 0

        var bestScore = if (isMaximizing) Int.MIN_VALUE else Int.MAX_VALUE
        var currentAlpha = alpha
        var currentBeta = beta

        for (button in boardList) {
            if (button.text.isEmpty()) {
                if (isMaximizing) {
                    button.text = CROSS
                    val score = minimax(false, currentAlpha, currentBeta)
                    bestScore = maxOf(bestScore, score)
                    currentAlpha = maxOf(currentAlpha, bestScore)
                    button.text = "" // Undo move
                    if (currentBeta <= currentAlpha) break // Beta cutoff
                } else {
                    button.text = NOUGHT
                    val score = minimax(true, currentAlpha, currentBeta)
                    bestScore = minOf(bestScore, score)
                    currentBeta = minOf(currentBeta, bestScore)
                    button.text = "" // Undo move
                    if (currentBeta <= currentAlpha) break // Alpha cutoff
                }
            }
        }
        return bestScore
    }

    private fun checkForVictory(symbol: String): Boolean {
        // Victory check logic
        if (match(binding.a1, symbol) && match(binding.a2, symbol) && match(binding.a3, symbol)) return true
        if (match(binding.b1, symbol) && match(binding.b2, symbol) && match(binding.b3, symbol)) return true
        if (match(binding.c1, symbol) && match(binding.c2, symbol) && match(binding.c3, symbol)) return true
        if (match(binding.a1, symbol) && match(binding.b1, symbol) && match(binding.c1, symbol)) return true
        if (match(binding.a2, symbol) && match(binding.b2, symbol) && match(binding.c2, symbol)) return true
        if (match(binding.a3, symbol) && match(binding.b3, symbol) && match(binding.c3, symbol)) return true
        if (match(binding.a1, symbol) && match(binding.b2, symbol) && match(binding.c3, symbol)) return true
        if (match(binding.a3, symbol) && match(binding.b2, symbol) && match(binding.c1, symbol)) return true

        return false
    }

    private fun match(button: Button, symbol: String): Boolean = button.text == symbol

    private fun result(title: String) {
        val message = "\nNoughts: $noughtsScore\nCrosses: $crossesScore"
        AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton("Reset") { _, _ -> resetBoard() }
            .setCancelable(false)
            .show()
    }

    private fun resetBoard() {
        for (button in boardList) {
            button.text = ""
        }
        currentTurn = Turn.NOUGHT // Reset to initial turn
        setTurnLabel()
    }

    private fun fullBoard(): Boolean {
        for (button in boardList) {
            if (button.text.isEmpty()) return false
        }
        return true
    }

    private fun addToBoard(button: Button) {
        if (button.text.isNotEmpty()) return

        button.text = if (currentTurn == Turn.NOUGHT) NOUGHT else CROSS
        currentTurn = if (currentTurn == Turn.NOUGHT) Turn.CROSS else Turn.NOUGHT
        setTurnLabel()
    }

    private fun setTurnLabel() {
        binding.turnTV.text = "Turn: ${if (currentTurn == Turn.CROSS) CROSS else NOUGHT}"
    }
}