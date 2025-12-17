package com.example.testthu.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.testthu.R
import com.example.testthu.databinding.DoimatkhauBinding

class ChangePasswordActivity : AppCompatActivity() {

    private lateinit var binding: DoimatkhauBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DoimatkhauBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Thiết lập sự kiện cho nút Đổi Mật Khẩu
        binding.btnChangePassword.setOnClickListener {
            changePassword()
        }
    }

    private fun changePassword() {
        val oldPassword = binding.editTextOldPassword.text.toString()
        val newPassword = binding.editTextNewPassword.text.toString()
        val confirmPassword = binding.editTextConfirmPassword.text.toString()

        // Kiểm tra các điều kiện trước khi đổi mật khẩu
        if (oldPassword.isEmpty() || newPassword.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show()
            return
        }

        if (newPassword != confirmPassword) {
            Toast.makeText(this, "Mật khẩu mới không khớp", Toast.LENGTH_SHORT).show()
            return
        }

        // Logic để đổi mật khẩu (thường là gọi API hoặc cập nhật cơ sở dữ liệu)
        // ...

        Toast.makeText(this, "Đổi mật khẩu thành công", Toast.LENGTH_SHORT).show()
        finish() // Kết thúc Activity sau khi đổi mật khẩu
    }
}