package com.example.testthu.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import com.example.testthu.R
import com.example.testthu.databinding.ActivityQuizziBinding
import com.example.testthu.databinding.DialogResultBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import kotlin.random.Random

class QuizzMath : AppCompatActivity()
{
    //binding
    private lateinit var binding : ActivityQuizziBinding
    private var isPlayed = false
    private var firstRandomNumber : Int ?= null
    private var secondRandomNumber : Int ?= null

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState : Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizziBinding.inflate(layoutInflater)
        setContentView(binding.root)
        //initViews
        binding.apply {
            btnStartOrNext.setOnClickListener {

                if (isPlayed)
                {
                    //Next Question
                    getRandomNumbers()
                    tvScore.text = (tvScore.text.toString().toInt()-1).toString()
                }else
                {
                    //start the Game
                    isPlayed = true
                    btnStartOrNext.text = "Next!"
                    cardQuestion.visibility = View.VISIBLE
                    cardScore.visibility = View.VISIBLE
                    getRandomNumbers()
                    runTimer()


                }

            }
            etAnswer.addTextChangedListener {
                val answer = firstRandomNumber!! + secondRandomNumber!!
                if (!it.isNullOrEmpty() && it.toString().toInt() == answer)
                {
                    //answer is true
                    tvScore.text = (tvScore.text.toString().toInt()+1).toString()
                    etAnswer.setText("")
                    getRandomNumbers()
                }
            }
        }
    }


    private fun runTimer() {
        lifecycleScope.launch(Dispatchers.IO)
        {
            (1..29).asFlow().onStart {

                binding.constraintLayout.transitionToEnd()

            }.onCompletion {
                //game finished. show dialog to user

                runOnUiThread {
                    binding.cardQuestion.visibility = View.GONE
                    val dialogBinding = DialogResultBinding.inflate(layoutInflater)
                    val dialog = Dialog(this@QuizzMath)
                    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
                    dialog.setContentView(dialogBinding.root)
                    dialog.setCancelable(false)
                    dialog.show()
                    dialog.window?.setLayout(ViewGroup.LayoutParams.MATCH_PARENT , ViewGroup.LayoutParams.WRAP_CONTENT)
                    dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

                    //clicks
                    dialogBinding.apply {
                        //show data in dialog
                        tvDialogScore.text = binding.tvScore.text
                        btnClose.setOnClickListener {
                            dialog.dismiss()
                            finish()
                        }
                        btnTryAgain.setOnClickListener {
                            dialog.dismiss()
                            binding.apply {
                                btnStartOrNext.text = getString(R.string.start_game)
                                cardQuestion.visibility = View.GONE
                                cardScore.visibility = View.GONE
                                isPlayed = false
                                constraintLayout.setTransition(R.id.start,R.id.end)
                                constraintLayout.transitionToEnd()
                                tvScore.text = "0"
                            }
                        }
                    }


                }

            }.collect{ delay(1000) }
        }

    }

    @SuppressLint("SetTextI18n")
    private fun getRandomNumbers()
    {
        firstRandomNumber = Random.nextInt(2,99)
        secondRandomNumber = Random.nextInt(2,99)
        binding.tvQuestionNumber.text = "$firstRandomNumber + $secondRandomNumber"
    }
}