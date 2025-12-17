package com.example.testthu.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.testthu.R
import com.example.testthu.databinding.ActivityCaroGameBinding


class CaroGameActivity : AppCompatActivity() {

    enum class Turn {
        NOUGHT,
        CROSS
    }

    private var firstTurn = Turn.CROSS
    private var currentTurn = Turn.CROSS
    private lateinit var binding: ActivityCaroGameBinding
    private val boardList = mutableListOf<Button>()

    private var crossesScore = 0
    private var noughtsScore = 0

    companion object {
        const val NOUGHT = "O"
        const val CROSS = "X"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCaroGameBinding.inflate(layoutInflater)
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
                result("Noughts Win!")
            } else if (checkForVictory(CROSS)) {
                crossesScore++
                result("Crosses Win!")
            } else if (fullBoard()) {
                result("Draw")
            }
        }
    }

    private fun checkForVictory(symbol: String): Boolean {
        // Horizontal Victory
        if (match(binding.a1, symbol) && match(binding.a2, symbol) && match(binding.a3, symbol)) return true
        if (match(binding.b1, symbol) && match(binding.b2, symbol) && match(binding.b3, symbol)) return true
        if (match(binding.c1, symbol) && match(binding.c2, symbol) && match(binding.c3, symbol)) return true

        // Vertical Victory
        if (match(binding.a1, symbol) && match(binding.b1, symbol) && match(binding.c1, symbol)) return true
        if (match(binding.a2, symbol) && match(binding.b2, symbol) && match(binding.c2, symbol)) return true
        if (match(binding.a3, symbol) && match(binding.b3, symbol) && match(binding.c3, symbol)) return true

        // Diagonal Victory
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
        currentTurn = if (firstTurn == Turn.NOUGHT) Turn.CROSS else Turn.NOUGHT
        firstTurn = currentTurn // Set the first turn to the current turn after reset
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