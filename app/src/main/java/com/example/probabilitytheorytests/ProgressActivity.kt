package com.example.probabilitytheorytests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.probabilitytheorytests.adapters.CategoryProgressAdapter
import com.example.probabilitytheorytests.data.CategoryProgress
import com.example.probabilitytheorytests.data.UserTestResult
import com.example.probabilitytheorytests.databinding.ActivityProgressBinding

class ProgressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProgressBinding
    private lateinit var categoriesAdapter: CategoryProgressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backToMainButton.setOnClickListener {
            finish()
        }

        initCategories()
    }

    private fun initCategories() {
        val categories = Repository.getAllCategories()
        val progressList = categories.map { category ->
            val testIds = Repository.getTestsByCategory(category.id).map { it.id }
            val testResults = Repository.getLatestUserTestResultsByTestIds(this, testIds)
            val (correctAnswers, totalQuestions) = calculateCategoryProgress(testResults)

            CategoryProgress(category.name, correctAnswers.toFloat() / totalQuestions)
        }

        categoriesAdapter = CategoryProgressAdapter()
        binding.resultsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.resultsRecyclerView.adapter = categoriesAdapter
        categoriesAdapter.submitList(progressList)
    }

    private fun calculateCategoryProgress(testResults: List<UserTestResult>): Pair<Int, Int> {
        var correctAnswers = 0
        var totalQuestions = 0

        for (result in testResults) {
            correctAnswers += result.correctAnswers
            totalQuestions += result.totalQuestions
        }

        return Pair(correctAnswers, totalQuestions)
    }
}