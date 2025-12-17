package com.example.testthu.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import android.widget.Toast
import com.example.testthu.R
import androidx.appcompat.app.AppCompatActivity

class Loaigame : AppCompatActivity() {

    private lateinit var tableLayout: TableLayout
    private val gameList = mutableListOf<Game>() // List to hold game data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        // Initialize the TableLayout
        tableLayout = findViewById(R.id.tableLayout)

        // Add initial game rows
        gameList.add(Game("Game trí tuệ"))
        gameList.add(Game("Game chiến thuật"))
        gameList.add(Game("Game nấu ăn"))
        gameList.add(Game("Game thời trang"))
        gameList.add(Game("Game bắn súng"))
        gameList.add(Game("Game đua xe"))
        gameList.add(Game("Game đào vàng"))
        gameList.add(Game("Game phiêu lưu"))
        gameList.add(Game("Game hành động"))
        gameList.add(Game("Game chú khỉ buồn"))
        // Display the game rows
        updateTable()
    }

    private fun updateTable() {
        tableLayout.removeAllViews() // Clear existing rows
        gameList.forEachIndexed { index, game ->
            addGameRow(index + 1, game.name)
        }
    }

    private fun addGameRow(rowIndex: Int, gameName: String) {
        // Inflate the row layout
        val inflater = LayoutInflater.from(this)
        val rowView: View = inflater.inflate(R.layout.item_game, null)

        // Find views in the row
        val sttTextView = rowView.findViewById<TextView>(R.id.sttTextView)
        val nameTextView = rowView.findViewById<TextView>(R.id.nameTextView)
        val editButton = rowView.findViewById<Button>(R.id.editButton)
        val deleteButton = rowView.findViewById<Button>(R.id.deleteButton)

        // Set values
        sttTextView.text = rowIndex.toString()
        nameTextView.text = gameName

        // Set button listeners for editing
        editButton.setOnClickListener {
            showEditDialog(rowIndex - 1)
        }

        // Set button listeners for deleting
        deleteButton.setOnClickListener {
            deleteGame(rowIndex - 1)
        }

        // Add the row to the TableLayout
        tableLayout.addView(rowView)
    }

    private fun showEditDialog(index: Int) {
        // Here you can implement a dialog to edit the game name
        val game = gameList[index]
        val editText = EditText(this)
        editText.setText(game.name)

        // Show dialog with edit text
        androidx.appcompat.app.AlertDialog.Builder(this)
            .setTitle("Edit Game")
            .setView(editText)
            .setPositiveButton("Save") { _, _ ->
                val newName = editText.text.toString()
                gameList[index].name = newName // Update the game name
                updateTable() // Refresh the table
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