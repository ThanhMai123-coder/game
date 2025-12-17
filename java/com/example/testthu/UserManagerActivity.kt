package com.example.testthu

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testthu.R
import com.example.testthu.data.User
import com.example.testthu.data.AppDatabase
import com.example.testthu.data.UserRepository
import com.example.testthu.databinding.ActivityUserManagementBinding
import com.example.testthu.ui.UserAdapter
import com.example.testthu.ui.UserViewModel
import com.example.testthu.ui.UserViewModelFactory
import java.security.MessageDigest

class UserManagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserManagementBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserManagementBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupRecyclerView()
        setupClickListeners()
        observeData()
    }

    private fun setupViewModel() {
        val database = AppDatabase.getDatabase(this)
        val userDao = database.userDao()
        val repository = UserRepository(userDao)
        val factory = UserViewModelFactory(repository)
        viewModel = ViewModelProvider(this, factory).get(UserViewModel::class.java)
    }

    private fun setupRecyclerView() {
        adapter = UserAdapter(
            users = emptyList(),
            onEditClick = { showEditDialog(it) },
            onDeleteClick = { viewModel.delete(it) }
        )
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@UserManagerActivity)
            adapter = this@UserManagerActivity.adapter
        }
    }

    private fun setupClickListeners() {
        binding.fabAdd.setOnClickListener {
            showAddDialog()
        }
    }

    private fun observeData() {
        viewModel.allUsers.observe(this) { users ->
            adapter.submitList(users)
            if (users.isEmpty()) {
                Toast.makeText(this, "No users found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun hashPassword(password: String): String {
        val digest = MessageDigest.getInstance("SHA-256")
        val hashedBytes = digest.digest(password.toByteArray())
        return hashedBytes.joinToString("") { String.format("%02x", it) }
    }

    private fun showAddDialog() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_user, null)
        val editUsername = dialogView.findViewById<EditText>(R.id.etusername)
        val editRole = dialogView.findViewById<EditText>(R.id.etrole)
        val editPassword = dialogView.findViewById<EditText>(R.id.etpassword)

        AlertDialog.Builder(this)
            .setTitle("Add User")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                val username = editUsername.text.toString().trim()
                val role = editRole.text.toString().trim()
                val password = editPassword.text.toString().trim()
                if (username.isNotBlank()) {
                    val hashedPassword = hashPassword(password)
                    val user = User(username = username, role = role, password = hashedPassword)
                    viewModel.insert(user)
                } else {
                    Toast.makeText(this, "Username cannot be empty", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("Cancel", null)
            .show()
    }

    private fun showEditDialog(user: User) {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_add_user, null)
        val editUsername = dialogView.findViewById<EditText>(R.id.etusername)
        val editRole = dialogView.findViewById<EditText>(R.id.etrole)
        val editPassword = dialogView.findViewById<EditText>(R.id.etpassword)

        editUsername.setText(user.username)
        editRole.setText(user.role)
        editPassword.setText(user.password)

        AlertDialog.Builder(this)
            .setTitle("Edit User")
            .setView(dialogView)
            .setPositiveButton("Save") { _, _ ->
                val updatedUser = user.copy(
                    username = editUsername.text.toString().trim(),
                    role = editRole.text.toString().trim(),
                    password = hashPassword(editPassword.text.toString())
                )
                viewModel.update(updatedUser)
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}