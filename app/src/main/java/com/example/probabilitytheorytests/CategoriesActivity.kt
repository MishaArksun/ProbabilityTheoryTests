package com.example.probabilitytheorytests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.probabilitytheorytests.adapters.CategoryAdapter
import com.example.probabilitytheorytests.databinding.ActivityCategoriesBinding

class CategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriesBinding
    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter { category ->
            // Обработка клика на категорию, например, переход к экрану со списком тестов
            val intent = Intent(this, TestsActivity::class.java)
            intent.putExtra("category", category)
            startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.backToMainButton.setOnClickListener {
            finish()
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.categoriesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.categoriesRecyclerView.adapter = categoryAdapter

        categoryAdapter.submitList(Repository.getCategories())
    }
}
