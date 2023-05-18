package com.example.probabilitytheorytests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.probabilitytheorytests.databinding.ActivityMainBinding
import android.content.Intent
import android.util.Log
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Инициализация SharedPreferences в Repository
        Repository.init(this)
        // Загрузка тестов и вопросов
        Repository.getTests(this)
        // Загрузка статуса вопросов из SharedPreferences
        Repository.loadQuestionsStatus()

        binding.testsButton.setOnClickListener {
            val intent = Intent(this, CategoriesActivity::class.java)
            startActivity(intent)
        }

        binding.progressButton.setOnClickListener {
            val intent = Intent(this, ProgressActivity::class.java)
            startActivity(intent)
        }
        binding.resetButton.setOnClickListener {
            Repository.resetTestResults()

            //Toast.makeText(this, "Результаты тестов были сброшены", Toast.LENGTH_SHORT).show()
        }
    }
}