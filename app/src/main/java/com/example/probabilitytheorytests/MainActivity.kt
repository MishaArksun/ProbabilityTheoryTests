package com.example.probabilitytheorytests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.probabilitytheorytests.adapters.TestAdapter
import com.example.probabilitytheorytests.data.Test
import com.example.probabilitytheorytests.databinding.ActivityMainBinding
import android.content.Intent
import com.example.probabilitytheorytests.data.Question
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val testAdapter: TestAdapter by lazy {
        TestAdapter { test ->
            // Обработка клика на тест, например, переход к другой активности для отображения вопросов теста
            val intent = Intent(this, TestActivity::class.java)
            intent.putExtra("testName", test.testName)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressButton.setOnClickListener {
            val intent = Intent(this, ProgressActivity::class.java)
            startActivity(intent)
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.testsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.testsRecyclerView.adapter = testAdapter

        testAdapter.submitList(Repository.getTests())
    }
}
