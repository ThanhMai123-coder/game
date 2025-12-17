package com.example.testthu.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testthu.R
import com.example.testthu.databinding.ActivityHosoBinding

class hoso : AppCompatActivity() {

    private lateinit var binding: ActivityHosoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHosoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Thiết lập sự kiện cho nút lưu thông tin
        binding.btnSave.setOnClickListener {
            saveProfile()
        }

        // Thiết lập sự kiện cho biểu tượng ba gạch
        binding.optionMenu.setOnClickListener { view ->
            showPopupMenu(view)
        }
    }

    private fun saveProfile() {
        val xungDanh = binding.editTextXungDanh.text.toString()
        val tenHienThi = binding.editTextTenHienThi.text.toString()
        val ngaySinh = binding.editTextNgaySinh.text.toString()
        val diaChi = binding.editTextDiaChi.text.toString()

        if (xungDanh.isEmpty() || tenHienThi.isEmpty() || ngaySinh.isEmpty() || diaChi.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        // Logic để lưu thông tin
        Toast.makeText(this, "Cập nhật thông tin thành công", Toast.LENGTH_SHORT).show()
    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu_options, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.item_change_password -> {
                    val intent = Intent(this, ChangePasswordActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.item_logout -> {
                    logout()
                    true
                }
                else -> false
            }
        }
        popupMenu.show()
    }

    private fun logout() {
        val intent = Intent(this, RoleSelectionActivity::class.java)
        startActivity(intent)
        finish()
    }
}