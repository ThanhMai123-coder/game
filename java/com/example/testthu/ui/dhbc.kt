package com.example.testthu.ui

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import com.example.testthu.R
import com.example.testthu.databinding.ActivityDhbcBinding

class dhbc : AppCompatActivity() {

    private lateinit var binding: ActivityDhbcBinding // Khai báo biến binding

    private val imageArray = arrayOf(
        R.drawable.ic_1,
        R.drawable.ic_2,
        R.drawable.ic_3,
        R.drawable.ic_4,
        R.drawable.ic_5,
        R.drawable.ic_6,
        R.drawable.ic_7,
        R.drawable.ic_8,
        R.drawable.ic_9,
        R.drawable.ic_10,
        R.drawable.ic_11,
        R.drawable.ic_12,
        R.drawable.ic_13,
        R.drawable.ic_14,
        R.drawable.ic_15,
        R.drawable.ic_16,
        R.drawable.ic_17,
        R.drawable.ic_18,
        R.drawable.ic_19,
        R.drawable.ic_20,
        R.drawable.ic_21,
        R.drawable.ic_22,
        R.drawable.ic_23,
        R.drawable.ic_24,
        R.drawable.ic_25,
        R.drawable.ic_26,
        R.drawable.ic_27,
        R.drawable.ic_28,
        R.drawable.ic_29,
        R.drawable.ic_30,
        R.drawable.ic_31,
        R.drawable.ic_32,
        R.drawable.ic_33,
        R.drawable.ic_34,
        R.drawable.ic_35
    )

    private val correctAnswers = arrayOf(
        "Soc Trang",
        "Bao cao",
        "Ca dao",
        "Ba hoa",
        "Cung cau",
        "Can bang",
        "Mat ma",
        "Neo don",
        "Khau cung",
        "Giay bac",
        "Hoa hau",
        "Hanh lang",
        "Tham thiet",
        "Obama",
        "Bong bay",
        "Nhat bao",
        "Co Loa",
        "Tranh thu",
        "Bi oi",
        "Xa kep",
        "Chi diem",
        "Kinh do",
        "Hung thu",
        "Dong cam cong kho",
        "Gach hoa",
        "Bao quat",
        "Noi gian",
        "Chan thanh",
        "Chan tuong",
        "Tinh truong",
        "My nhan ngu",
        "Trai cay",
        "Hai long",
        "Rua tien",
        "De tien"
    )

    private var randomIndices = mutableListOf<Int>() // Danh sách chỉ số ngẫu nhiên
    private var currentScore = 0 // Điểm hiện tại
    private var currentImageIndex = 0 // Chỉ số hình ảnh hiện tại

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDhbcBinding.inflate(layoutInflater) // Khởi tạo binding
        setContentView(binding.root) // Sử dụng binding.root

        // Khởi tạo danh sách chỉ số ngẫu nhiên
        randomIndices = (0 until imageArray.size).shuffled().toMutableList()
        loadNewQuestion()

        binding.submitButton.setOnClickListener {
            // Xử lý khi nhấn nút gửi
            val answer = binding.answerEditText.text.toString().trim()
            // Kiểm tra câu trả lời và hiển thị kết quả
            if (answer.equals(correctAnswers[randomIndices[currentImageIndex]], ignoreCase = true)) {
                currentScore += 1 // Cộng điểm nếu đúng
                binding.resultTextView.text = "Dung! Diem hien tai: $currentScore"
            } else {
                currentScore /= 2 // Trừ điểm nếu sai
                binding.resultTextView.text = "Sai, dap an dung la: ${correctAnswers[randomIndices[currentImageIndex]]}. Diem hien tai: $currentScore"
            }

            // Cập nhật điểm trong scoreTextView
            binding.scoreTextView.text = "XẾP: $currentScore"

            // Chuyển đến hình ảnh tiếp theo
            currentImageIndex++
            if (currentImageIndex < randomIndices.size) {
                loadNewQuestion()
            } else {
                binding.resultTextView.text = "Game ket thuc! Diem cuoi: $currentScore"
                binding.submitButton.isEnabled = false // Vô hiệu hóa nút gửi
            }
        }

        // Thiết lập sự kiện cho các nút chữ cái
        setUpLetterButtons()

        // Thiết lập sự kiện cho nút "Dấu cách"
        binding.gridLayout.findViewById<Button>(R.id.spaceButton).setOnClickListener {
            binding.answerEditText.append(" ") // Thêm khoảng trắng vào trường nhập
        }
    }

    private fun loadNewQuestion() {
        binding.questionImageView.setImageResource(imageArray[randomIndices[currentImageIndex]])
        binding.resultTextView.text = "" // Xóa kết quả cũ
        binding.answerEditText.text.clear() // Xóa câu trả lời cũ
    }


    private fun setUpLetterButtons() {
        val letterButtons = mutableListOf<Button>()

        // Lặp qua tất cả các con cái của GridLayout và thêm vào danh sách
        for (i in 0 until binding.gridLayout.childCount) {
            val button = binding.gridLayout.getChildAt(i) as? Button
            button?.let { letterButtons.add(it) }
        }

        // Kiểm tra số lượng nút
        if (letterButtons.size != 25) {
            throw IllegalStateException("Expected 25 letter buttons, but found ${letterButtons.size}.")
        }

        // Thiết lập sự kiện cho từng nút chữ cái
        for (button in letterButtons) {
            button.setOnClickListener {
                val letter = (it as Button).text.toString() // Lấy chữ cái từ nút
                binding.answerEditText.append(letter) // Thêm chữ cái vào trường nhập
            }
        }
    }
}