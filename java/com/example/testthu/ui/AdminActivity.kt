package com.example.testthu.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import androidx.appcompat.widget.Toolbar
import com.example.testthu.ui.Loaigame
import com.example.testthu.ui.ManagementActivity
import  com.example.testthu.ui.Thongtin
import  com.example.testthu.ui.Trangchu
import com.example.testthu.R


class AdminActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.testthu.R.layout.activity_admin)

        val toolbar: Toolbar = findViewById(com.example.testthu.R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(com.example.testthu.R.id.drawer_layout)
        val navigationView: NavigationView = findViewById(com.example.testthu.R.id.navigation_view)

        // Xử lý sự kiện chọn menu
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                com.example.testthu.R.id.nav_dashboard -> {
                    // Xử lý trang chủ Admin
                    Toast.makeText(this, "Trang chủ Admin", Toast.LENGTH_SHORT).show()
                }
                com.example.testthu.R.id.nav_management -> {
                    // Xử lý quản trị danh mục game
                    Toast.makeText(this, "Quản trị danh mục game", Toast.LENGTH_SHORT).show()
                }
                com.example.testthu.R.id.nav_game_type -> {
                    loaigame() // Chuyển đến Game loại
                    true
                }
                com.example.testthu.R.id.nav_game -> {
                    game() // Chuyển đến Game loại
                    true
                }
                com.example.testthu.R.id.nav_binhluan -> {
                    binhluan() // Chuyển đến Game loại
                    true
                }


                com.example.testthu.R.id.nav_user_config -> {
                    // Xử lý cấu hình user
                    Toast.makeText(this, "Cấu hình user", Toast.LENGTH_SHORT).show()
                }

                com.example.testthu.R.id.nav_user_roles -> {
                qlUser()

                    true
                }
                com.example.testthu.R.id.nav_admin -> {
                   thongtin()
                    true
                }
                com.example.testthu.R.id.nav_logout -> {
                    logout()
                    true
                }
            }
            drawerLayout.closeDrawers() // Đóng menu
            true
        }
    }

    private fun loaigame() {

        val intent = Intent(this, com.example.testthu.ui.Loaigame::class.java)
        startActivity(intent)
    }
    private fun binhluan() {

        val intent = Intent(this, com.example.testthu.ui.CommentAdmin::class.java)
        startActivity(intent)
    }
    private fun game() {
        val intent = Intent(this, com.example.testthu.ui.ManagementActivity::class.java)
        startActivity(intent)
    }
    private fun thongtin() {
        val intent = Intent(this, com.example.testthu.ui.Thongtin::class.java)
        startActivity(intent)
    }
    private fun qlUser() {
        val intent = Intent(this, com.example.testthu.UserManagerActivity::class.java)
        startActivity(intent)
    }
    private fun logout() {
        val intent = Intent(this, com.example.testthu.ui.Trangchu::class.java)
        startActivity(intent)
        finish()
    }
}