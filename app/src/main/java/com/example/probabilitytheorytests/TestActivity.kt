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

        val testName = intent.getStringExtra("testName") ?: ""
        val test = Repository.getTests().find { it.testName == testName }

        if (test != null) {
            questionAdapter.submitList(test.questions)
        } else {
            Toast.makeText(this, "Тест не найден", Toast.LENGTH_LONG).show()// Обработать ситуацию, когда тест не найден, например, показать сообщение об ошибке или вернуться к предыдущей активности
        }
    }

    private fun initRecyclerView() {
        binding.questionsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.questionsRecyclerView.adapter = questionAdapter
    }

    private fun calculateResult(): TestResult {
        val testName = intent.getStringExtra("testName") ?: ""
        val correctAnswers = questionAdapter.currentList.count { it.isAnswerCorrect }
        return TestResult(testName, correctAnswers, questionAdapter.itemCount)
    }

    fun saveTestResult(testResult: TestResult) {
        val sharedPreferences = getSharedPreferences("test_results", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val uniqueKey = "${testResult.testName}_${System.currentTimeMillis()}"
        editor.putInt(uniqueKey, testResult.correctAnswers)
        editor.apply()
    }
}