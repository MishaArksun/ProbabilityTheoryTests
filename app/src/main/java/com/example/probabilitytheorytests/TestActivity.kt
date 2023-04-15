package com.example.probabilitytheorytests

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.probabilitytheorytests.adapters.QuestionAdapter
import com.example.probabilitytheorytests.databinding.ActivityTestBinding
import org.threeten.bp.LocalDateTime
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.probabilitytheorytests.data.Test
import com.example.probabilitytheorytests.data.UserTestResult
import com.google.gson.Gson

class TestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestBinding
    private val questionAdapter = QuestionAdapter()
    private var test: Test? = null
    private var testId: Int = -1

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        binding.finishTestButton.setOnClickListener {
            val testResult = calculateResult()
            val message = "Тест завершен. Правильных ответов: ${testResult.correctAnswers} из ${questionAdapter.itemCount}"
            Toast.makeText(this, message, Toast.LENGTH_LONG).show()

            saveTestResult(testResult)
            finish()
        }

        testId = intent.getIntExtra("testId", -1)
        test = Repository.getTests().find { it.id == testId }

        if (test != null) {
            questionAdapter.submitList(test!!.questions)
        } else {
            Toast.makeText(this, "Тест не найден", Toast.LENGTH_LONG).show()
        }
    }

    private fun initRecyclerView() {
        binding.questionsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.questionsRecyclerView.adapter = questionAdapter
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun calculateResult(): UserTestResult {
        val correctAnswers = questionAdapter.currentList.count { it.isAnswerCorrect }
        return UserTestResult(testId, test?.testName ?: "", LocalDateTime.now(), correctAnswers, questionAdapter.itemCount)
    }

    private fun saveTestResult(testResult: UserTestResult) {
        val sharedPreferences = getSharedPreferences("test_results", MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val userTestResultJson = gson.toJson(testResult)
        val uniqueKey = "${testResult.testName}_${System.currentTimeMillis()}"
        editor.putString(uniqueKey, userTestResultJson)
        editor.apply()
    }
}
