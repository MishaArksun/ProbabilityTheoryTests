package com.example.probabilitytheorytests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.probabilitytheorytests.adapters.CategoryProgressAdapter
import com.example.probabilitytheorytests.databinding.ActivityProgressBinding
import com.example.probabilitytheorytests.data.CategoryProgress

class ProgressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProgressBinding
    private val categoryProgressAdapter = CategoryProgressAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()

        binding.backToMainButton.setOnClickListener {
            finish()
        }

        binding.recommendedTestButton.setOnClickListener {
            val recommendedQuestions = Repository.getRecommendedQuestions()

            val intent = Intent(this@ProgressActivity, TestActivity::class.java)
            intent.putParcelableArrayListExtra("questions", ArrayList(recommendedQuestions))
            startActivity(intent)
        }

        updateProgress()
    }

    override fun onResume() {
        super.onResume()
        updateProgress()
    }

    private fun updateProgress() {
        val categories = Repository.getCategories(this)
        val categoryProgressList = categories.map { category ->
            val (correctAnswers, totalQuestions) = Repository.getCategoryStatistics(category.id)
            CategoryProgress(category.name, correctAnswers.toFloat() / totalQuestions)
        }
        categoryProgressAdapter.submitList(categoryProgressList)
    }

    private fun initRecyclerView() {
        binding.resultsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.resultsRecyclerView.adapter = categoryProgressAdapter
    }
}