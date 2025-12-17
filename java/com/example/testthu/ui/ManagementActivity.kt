package com.example.testthu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import com.example.testthu.R
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class ManagementActivity : AppCompatActivity() {

    private lateinit var tableLayout: TableLayout
    private val gameList = mutableListOf<Game>() // List to hold game data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ggame)

        tableLayout = findViewById(R.id.tableLayout)

        // Initialize game entries
        initializeGames()
        updateTable()
    }

    private fun initializeGames() {
        gameList.add(Game("Game chú khỉ buồn 12"))
        gameList.add(Game("Game trang điểm công chúa"))
        gameList.add(Game("Game nấu ăn giáng sinh"))
        gameList.add(Game("Game tic tac toe"))
        gameList.add(Game("Game quizz math"))
        gameList.add(Game("Game đuổi hình bắt chữ"))
        gameList.add(Game("Game đào vàng"))
        gameList.add(Game("Game đường đua tốc độ"))
    }

    private fun updateTable() {
        tableLayout.removeAllViews() // Clear existing rows
        gameList.forEachIndexed { index, game ->
            addGameRow(index + 1, game.name)
        }
    }

    private fun addGameRow(rowIndex: Int, gameName: String) {
        val inflater = LayoutInflater.from(this)
        val rowView: View = inflater.inflate(R.layout.item_game, null)

        val sttTextView = rowView.findViewById<TextView>(R.id.sttTextView)
        val nameTextView = rowView.findViewById<TextView>(R.id.nameTextView)
        val editButton = rowView.findViewById<Button>(R.id.editButton)
        val deleteButton = rowView.findViewById<Button>(R.id.deleteButton)

        sttTextView.text = rowIndex.toString()
        nameTextView.text = gameName

        editButton.setOnClickListener {
            showEditDialog(rowIndex - 1) // Pass the index for editing
        }

        deleteButton.setOnClickListener {
            deleteGame(rowIndex - 1) // Pass the index for deletion
        }

        tableLayout.addView(rowView)
    }

    private fun showEditDialog(index: Int) {
        val game = gameList[index]
        val builder = AlertDialog.Builder(this)
        val input = TextView(this)
        input.text = game.name

        builder.setTitle("Edit Game")
            .setMessage("Edit the name of the game:")
            .setView(input)
            .setPositiveButton("Save") { _, _ ->
                gameList[index].name = input.text.toString() // Update game name
                updateTable() // Refresh table
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun deleteGame(index: Int) {
        gameList.removeAt(index) // Remove the game from the list
        updateTable() // Refresh the table
        Toast.makeText(this, "Game deleted", Toast.LENGTH_SHORT).show()
    }

    data class Game(var name: String) // Data class to hold game info
}