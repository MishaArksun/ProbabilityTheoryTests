package com.example.probabilitytheorytests

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.probabilitytheorytests.adapters.TestAdapter
import com.example.probabilitytheorytests.data.Category
import com.example.probabilitytheorytests.databinding.ActivityTestsBinding

class TestsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestsBinding
    private val testAdapter: TestAdapter by lazy {
        TestAdapter { test ->
            val intent = Intent(this, TestActivity::class.java)
            intent.putExtra("testId", test.id)
            startActivity(intent)
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val category = intent.getParcelableExtra<Category>("category")
        if (category != null) {
            title = category.name
            initRecyclerView(category)
        }
        binding.backToMainButton.setOnClickListener {
            finish()
        }
    }

    private fun initRecyclerView(category: Category) {
        val gridLayoutManager = GridLayoutManager(this, 2)
        binding.testsRecyclerView.layoutManager = gridLayoutManager
        binding.testsRecyclerView.adapter = testAdapter

        val filteredTests = Repository.getTests(this).filter { it.categoryId == category.id }
        testAdapter.submitList(filteredTests)
    }
}