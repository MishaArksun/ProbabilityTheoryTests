package com.example.probabilitytheorytests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.probabilitytheorytests.adapters.QuestionAdapter
import com.example.probabilitytheorytests.data.Question
import com.example.probabilitytheorytests.databinding.ActivityTestBinding
import android.widget.Toast

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding
    private val questionAdapter = QuestionAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        fun saveTestResult(testResult: TestResult) {
            // Здесь вы можете сохранить результат теста, например, в SharedPreferences, файле или базе данных
            // Пример сохранения в SharedPreferences:
            val sharedPreferences = getSharedPreferences("test_results", MODE_PRIVATE)
            val editor = sharedPreferences.edit()
            editor.putInt(testResult.testName, testResult.correctAnswers)
            editor.apply()
        }
        binding.finishTestButton.setOnClickListener {
            val testResult = calculateResult()
            val message = "Тест завершен. Правильных ответов: ${testResult.correctAnswers} из ${testResult.totalQuestions}"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()

            saveTestResult(testResult)
            finish()
        }


    }

    private fun initRecyclerView() {
        binding.questionsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.questionsRecyclerView.adapter = questionAdapter

        val questions = listOf(
            Question("Вопрос 1", listOf("Вариант ответа 1", "Вариант ответа 2"), correctAnswerIndex = 0),
            Question("Вопрос 2", listOf("Вариант ответа 1", "Вариант ответа 2"), correctAnswerIndex = 0),
            Question("Вопрос 3", listOf("Вариант ответа 1", "Вариант ответа 2"), correctAnswerIndex = 0),
            Question("Вопрос 4", listOf("Вариант ответа 1", "Вариант ответа 2"), correctAnswerIndex = 0),
        )

        questionAdapter.submitList(questions)
    }
    private fun calculateResult(): TestResult {
        val correctAnswers = questionAdapter.currentList.count { it.isAnswerCorrect }
        return TestResult("Название теста", correctAnswers, questionAdapter.itemCount)
    }
}