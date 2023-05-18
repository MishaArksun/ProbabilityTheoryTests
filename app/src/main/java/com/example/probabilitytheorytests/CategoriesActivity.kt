package com.example.probabilitytheorytests

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.probabilitytheorytests.adapters.CategoryAdapter
import com.example.probabilitytheorytests.data.Category
import com.example.probabilitytheorytests.databinding.ActivityCategoriesBinding

class CategoriesActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCategoriesBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val categories: List<Category> = Repository.getCategories(this)

        val adapter = CategoryAdapter { category ->
            val intent = Intent(this, TestsActivity::class.java)
            intent.putExtra(TestsActivity.EXTRA_CATEGORY_ID, category.id)
            startActivity(intent)
        }

        binding.categoriesRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.categoriesRecyclerView.adapter = adapter

        adapter.submitList(categories)
        binding.backToMainButton.setOnClickListener {
            finish()
        }
    }
}