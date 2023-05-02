package com.example.quizapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.quizapp.databinding.ActivityMainBinding
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.Random

class MainActivity : AppCompatActivity() {

    var quizList = ArrayList<QuizModel>()
    lateinit var random: Random
    var currScore = 0
    var questionAttempted: Int = 1
    lateinit var binding: ActivityMainBinding
    var ansClick = false
    var currPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        random = Random()
//        currPos = 1
        getQuestion(quizList)
        setDataToViews(currPos)

        binding.option1.setOnClickListener {
            if (!ansClick) {
                ansClick = true
                if (quizList.get(currPos).answer.trim().equals(binding.option1.text.toString().trim(), ignoreCase = true))
                {
                    currScore++
                    binding.option1.setBackgroundColor(Color.GREEN)
                } else {
                    binding.option1.setBackgroundColor(Color.RED)
                }
                if (quizList.get(currPos).answer.trim().equals(binding.option2.text.toString().trim(), ignoreCase = true))
                {
                    binding.option2.setBackgroundColor(Color.GREEN)
                }
                if (quizList.get(currPos).answer.trim().equals(binding.option3.text.toString().trim(), ignoreCase = true))
                {
                    binding.option3.setBackgroundColor(Color.GREEN)
                }
                if (quizList.get(currPos).answer.trim().equals(binding.option4.text.toString().trim(), ignoreCase = true))
                {
                    binding.option4.setBackgroundColor(Color.GREEN)
                }

                questionAttempted++
            }
        }

        binding.option2.setOnClickListener {

            if (!ansClick) {
                ansClick = true
                if (quizList.get(currPos).answer.trim().equals(
                        binding.option2.text.toString()
                            .trim(), ignoreCase = true
                    )
                ) {
                    currScore++
                    binding.option2.setBackgroundColor(Color.GREEN)
                } else {
                    binding.option2.setBackgroundColor(Color.RED)
                }
                if (quizList.get(currPos).answer.trim().equals(binding.option1.text.toString().trim(), ignoreCase = true))
                {
                    binding.option1.setBackgroundColor(Color.GREEN)
                }
                if (quizList.get(currPos).answer.trim().equals(binding.option3.text.toString().trim(), ignoreCase = true))
                {
                    binding.option3.setBackgroundColor(Color.GREEN)
                }
                if (quizList.get(currPos).answer.trim().equals(binding.option4.text.toString().trim(), ignoreCase = true))
                {
                    binding.option4.setBackgroundColor(Color.GREEN)
                }
                questionAttempted++
            }
        }

        binding.option3.setOnClickListener {
            if (!ansClick) {
                ansClick = true
                if (quizList.get(currPos).answer.trim().equals(
                        binding.option3.text.toString()
                            .trim(), ignoreCase = true
                    )
                ) {
                    currScore++
                    binding.option3.setBackgroundColor(Color.GREEN)
                } else {
                    binding.option3.setBackgroundColor(Color.RED)
                }
                if (quizList.get(currPos).answer.trim().equals(binding.option2.text.toString().trim(), ignoreCase = true))
                {
                    binding.option2.setBackgroundColor(Color.GREEN)
                }
                if (quizList.get(currPos).answer.trim().equals(binding.option1.text.toString().trim(), ignoreCase = true))
                {
                    binding.option1.setBackgroundColor(Color.GREEN)
                }
                if (quizList.get(currPos).answer.trim().equals(binding.option4.text.toString().trim(), ignoreCase = true))
                {
                    binding.option4.setBackgroundColor(Color.GREEN)
                }
                questionAttempted++
            }
        }

        binding.option4.setOnClickListener {
            if (!ansClick) {
                ansClick = true
                if (quizList.get(currPos).answer.trim().equals(
                        binding.option4.text.toString()
                            .trim(), ignoreCase = true
                    )
                ) {
                    currScore++
                    binding.option4.setBackgroundColor(Color.GREEN)
                } else {
                    binding.option4.setBackgroundColor(Color.RED)
                }
                if (quizList.get(currPos).answer.trim().equals(binding.option2.text.toString().trim(), ignoreCase = true))
                {
                    binding.option2.setBackgroundColor(Color.GREEN)
                }
                if (quizList.get(currPos).answer.trim().equals(binding.option3.text.toString().trim(), ignoreCase = true))
                {
                    binding.option3.setBackgroundColor(Color.GREEN)
                }
                if (quizList.get(currPos).answer.trim().equals(binding.option1.text.toString().trim(), ignoreCase = true))
                {
                    binding.option1.setBackgroundColor(Color.GREEN)
                }
                questionAttempted++
            }
        }

        binding.nxtQuestion.setOnClickListener {
            ansClick=false
            currPos++
            setDataToViews(currPos)
            binding.option1.setBackgroundColor(Color.BLUE)
            binding.option2.setBackgroundColor(Color.BLUE)
            binding.option3.setBackgroundColor(Color.BLUE)
            binding.option4.setBackgroundColor(Color.BLUE)
        }
    }

    private fun showBottomShitDialog() {
        val bottomSheetDialog = BottomSheetDialog(
            this,
            com.google.android.material.R.style.Base_Theme_Material3_Light_BottomSheetDialog
        )
        bottomSheetDialog.setContentView(R.layout.dialog_item)

        var tv = bottomSheetDialog.findViewById<TextView>(R.id.tvDialogScore)
        var restart = bottomSheetDialog.findViewById<Button>(R.id.btnDialogRestart)

        tv?.text = "Your Score is $currScore /5"

        restart?.setOnClickListener {
            currPos = 0
            setDataToViews(currPos)
            questionAttempted = 1
            currScore = 0
            bottomSheetDialog.dismiss()
        }
        bottomSheetDialog.setCancelable(false)
        bottomSheetDialog.show()
    }

    private fun setDataToViews(pos: Int) {

        if (questionAttempted == 5) {
            binding.tvnq.text = "Finish"
            binding.imgArrow.visibility = View.GONE
        } else {
            binding.tvnq.text = "Next Question"
            binding.imgArrow.visibility = View.VISIBLE
        }
        if (questionAttempted == 6) {
            showBottomShitDialog()
        } else {

            binding.questionNoTv.text = "Questions Attempted $questionAttempted /5"
            binding.tvQuestion.text = quizList[pos].question
            binding.option1.text = quizList[pos].option1
            binding.option2.text = quizList[pos].option2
            binding.option3.text = quizList[pos].option3
            binding.option4.text = quizList[pos].option4
        }
    }

    private fun getQuestion(list: ArrayList<QuizModel>) {

        list.add(
            QuizModel(
                "Who is CEO of Google ? ",
                "Mark Zuckerberg",
                "Bil Gates",
                "Elon Musk",
                "Sundar Pichai",
                "Sundar Pichai"
            )
        )
        list.add(
            QuizModel(
                "Who is Founder of Scaler ? ",
                "Nishant Chahar",
                "Anshuman Singh",
                "Virat Kohli",
                "Anuj Bhaiya",
                "Anshuman Singh"
            )
        )
        list.add(
            QuizModel(
                "Who invented electricity ? ",
                "Benjamin Franklin",
                "Newton",
                "Albert intestine",
                "Donald Trump",
                "Benjamin Franklin"
            )
        )
        list.add(
            QuizModel(
                "Who is founder of Tesla ? ",
                "Ronaldo",
                "Ding Liren",
                "Elon Musk",
                "Donlad Trump",
                "Elon Musk"
            )
        )
        list.add(
            QuizModel(
                "Who Found C language",
                "Dennis Ritchie",
                "Bil Gates",
                "Bjarne Stroustrup",
                "James Gosling",
                "Dennis Ritchie"
            )
        )
    }
}