package com.example.testthu.ui

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.testthu.R

class CategoryActivity : AppCompatActivity() {

    private lateinit var editTextCategory: EditText
    private lateinit var editTextProduct: EditText
    private lateinit var editTextTitle: EditText
    private lateinit var editTextSlug: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        editTextCategory = findViewById(R.id.edit_text_category)
        editTextProduct = findViewById(R.id.edit_text_product)
        editTextTitle = findViewById(R.id.edit_text_title)
        editTextSlug = findViewById(R.id.edit_text_slug)

        val buttonSelectImage: Button = findViewById(R.id.button_select_image)
        val buttonSave: Button = findViewById(R.id.button_save)

        buttonSelectImage.setOnClickListener {
            // Xử lý chọn hình ảnh
            Toast.makeText(this, "Chọn hình đại diện", Toast.LENGTH_SHORT).show()
        }

        buttonSave.setOnClickListener {
            // Xử lý lưu thông tin
            val category = editTextCategory.text.toString()
            val product = editTextProduct.text.toString()
            val title = editTextTitle.text.toString()
            val slug = editTextSlug.text.toString()

            // Ví dụ xử lý lưu thông tin
            Toast.makeText(this, "Đã lưu: $category, $product, $title, $slug", Toast.LENGTH_SHORT).show()
        }
    }
}