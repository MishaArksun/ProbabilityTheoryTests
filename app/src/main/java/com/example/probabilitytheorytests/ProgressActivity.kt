package com.example.probabilitytheorytests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.probabilitytheorytests.adapters.TestResultAdapter
import com.example.probabilitytheorytests.data.Test
import com.example.probabilitytheorytests.databinding.ActivityProgressBinding

class ProgressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProgressBinding
    private val testResultAdapter = TestResultAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        val sharedPreferences = getSharedPreferences("test_results", MODE_PRIVATE)
        val testResults = Repository.getTests().map { test ->
            val correctAnswers = sharedPreferences.getInt(test.testName, -1)
            if (correctAnswers >= 0) {
                TestResult(test.testName, correctAnswers, test.questions.size)
            } else {
                null
            }
        }.filterNotNull()

        testResultAdapter.submitList(testResults)

        binding.progressButton.setOnClickListener {
            finish()
        }
    }

    private fun initRecyclerView() {
        binding.testResultsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.testResultsRecyclerView.adapter = testResultAdapter
    }
}