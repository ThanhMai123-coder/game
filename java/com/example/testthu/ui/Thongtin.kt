package com.example.testthu.ui
import com.example.testthu.R
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class Thongtin : AppCompatActivity() {

    private lateinit var usernameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var nationalityEditText: EditText
    private lateinit var sendEmailCheckBox: CheckBox
    private lateinit var createUserButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_settings) // Tên tệp layout XML

        // Khởi tạo các view
        usernameEditText = findViewById(R.id.usernameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        nationalityEditText = findViewById(R.id.domainEditText)
        sendEmailCheckBox = findViewById(R.id.sendEmailCheckBox)
        createUserButton = findViewById(R.id.createUserButton)

        // Thiết lập sự kiện cho nút cập nhật
        createUserButton.setOnClickListener {
            updateUserInfo()
        }
    }

    private fun updateUserInfo() {
        val username = usernameEditText.text.toString()
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()
        val nationality = nationalityEditText.text.toString()
        val sendEmail = sendEmailCheckBox.isChecked

        // Kiểm tra thông tin nhập vào
        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || nationality.isEmpty()) {
            Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show()
            return
        }


        Toast.makeText(this, "Thông tin đã được cập nhật!", Toast.LENGTH_SHORT).show()
    }
}