package com.example.testthu.ui

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity

import com.example.testthu.databinding.TrangchuBinding
import com.example.testthu.R
import com.example.testthu.databinding.Trangchu2Binding

class NextPageActivity : AppCompatActivity() {

    private lateinit var binding: Trangchu2Binding // Khai báo binding cho layout trangchu2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = Trangchu2Binding.inflate(layoutInflater) // Khởi tạo binding với layout trangchu2
        setContentView(binding.root)

        // Thiết lập sự kiện cho nút trở về trang đầu
        binding.btnFirstpage.setOnClickListener {
            navigateToFirstPage()
        }


        // Thiết lập sự kiện cho biểu tượng menu
        binding.textMenu.setOnClickListener { // Sử dụng ID cho TextView chứa biểu tượng ba chấm
            showMenu(it)
        }
        binding.textHome.setOnClickListener {
            navigateToCommentUser()
        }
    }

    private fun showMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.menuInflater.inflate(R.menu.menu, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item: MenuItem ->
            when (item.itemId) {
                R.id.item1 -> {
                    navigateToStrategyGame() // Chuyển đến Game chiến thuật
                    true
                }
                R.id.item2 -> {
                    navigateToPuzzleGame() // Chuyển đến Game Trí tuệ
                    true
                }
                R.id.item3 -> {
                    navigateToCookingGame() // Chuyển đến Game Nấu ăn
                    true
                }
                R.id.item4 -> {
                    navigateToFashionGame() // Chuyển đến Game Thời trang
                    true
                }
                R.id.item5 -> {
                    navigateToOtherGames() // Chuyển đến Game khác
                    true
                }
                R.id.item6 -> {
                    navigateTohsnd() // Chuyển đến Game khác
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }


    private fun navigateToStrategyGame() {
        val intent = Intent(this, gamekhac::class.java)
        startActivity(intent)
    }

    private fun navigateToPuzzleGame() {
        val intent = Intent(this, MainActivit::class.java)
        startActivity(intent)
    }

    private fun navigateToCookingGame() {
        val intent = Intent(this, gamekhac::class.java)
        startActivity(intent)
    }

    private fun navigateToFashionGame() {
        val intent = Intent(this, gamekhac::class.java)
        startActivity(intent)
    }

    private fun navigateToOtherGames() {
        val intent = Intent(this, gamekhac::class.java)
        startActivity(intent)
    }
    private fun navigateTohsnd() {
        val intent = Intent(this, hoso::class.java)
        startActivity(intent)
    }
    private fun navigateToFirstPage() {
        val intent = Intent(this, Trangchu::class.java)
        startActivity(intent)
    }
    private fun navigateToCommentUser() {
        val intent = Intent(this, CommentUser::class.java) // Chuyển đến CommentUser
        startActivity(intent)
    }
}