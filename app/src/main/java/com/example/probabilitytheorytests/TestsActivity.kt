package com.example.probabilitytheorytests

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.probabilitytheorytests.adapters.TestAdapter
import com.example.probabilitytheorytests.databinding.ActivityTestsBinding

class TestsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTestsBinding
    private val testAdapter = TestAdapter { test ->
        val intent = Intent(this, TestActivity::class.java)
        intent.putExtra("testId", test.id)
        startActivity(intent)
    }

    private var categoryId: Int = -1

    companion object {
        const val EXTRA_CATEGORY_ID = "extra_category_id"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTestsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        categoryId = intent.getIntExtra(EXTRA_CATEGORY_ID, -1)

        initRecyclerView()
        loadTests()
        binding.backToMainButton.setOnClickListener {
            finish()
        }
    }

    private fun initRecyclerView() {
        binding.testsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.testsRecyclerView.adapter = testAdapter
    }

    private fun loadTests() {
        val tests = Repository.getTestsByCategoryId(categoryId)
        testAdapter.submitList(tests)
    }

}