package com.example.testthu.ui

data class Use(
    val id: Int,
    val name: String,
    val role: String,
    var isSelected: Boolean = false // Track checkbox state
)