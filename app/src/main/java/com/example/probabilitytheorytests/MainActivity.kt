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
            intent.putExtra("test", test)
            startActivity(intent)
        }
    }
    private val tests = listOf(
        Test(
            testName = "Основы вероятности",
            questions = listOf(
                Question(
                    question = "Вероятность невозможного события равна...",
                    options = listOf("0", "1", "0.5", "не определена"),
                    correctAnswerIndex = 0
                ),
                Question(
                    question = "Вероятность достоверного события равна...",
                    options = listOf("0", "1", "0.5", "не определена"),
                    correctAnswerIndex = 1
                ),
                Question(
                    question = "Сумма вероятностей всех элементарных исходов равна...",
                    options = listOf("0", "1", "0.5", "не определена"),
                    correctAnswerIndex = 1
                ),
                Question(
                    question = "Как называется множество всех элементарных исходов?",
                    options = listOf("Событие", "Пространство исходов", "Вероятность", "Комбинация"),
                    correctAnswerIndex = 1
                )
            )
        ),
        Test(
            testName = "Случайные события",
            questions = listOf(
                Question(
                    question = "Какое из следующих событий является случайным?",
                    options = listOf("Восход солнца", "Бросок монеты", "Приход зарплаты", "Рост дерева"),
                    correctAnswerIndex = 1
                ),
                Question(
                    question = "Что такое совместные события?",
                    options = listOf("События, которые могут произойти вместе", "События, которые никогда не происходят вместе", "События, которые происходят одновременно", "События, которые происходят случайно"),
                    correctAnswerIndex = 0
                ),
                Question(
                    question = "События A и B являются противоположными, если...",
                    options = listOf("P(A) + P(B) = 1", "P(A) * P(B) = 0", "P(A ∩ B) = 0", "P(A ∪ B) = 1"),
                    correctAnswerIndex = 0
                ),
                Question(
                    question = "События, которые не могут произойти одновременно, называются...",
                    options = listOf("несовместными", "совместными", "зависимыми", "независимыми"),
                    correctAnswerIndex = 0
                )
            )
        ),
    )

    /*

    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.progressButton.setOnClickListener {
            val intent = Intent(this, ProgressActivity::class.java)
            intent.putExtra("tests", tests.toTypedArray())
            startActivity(intent)
        }

        initRecyclerView()
    }

    private fun initRecyclerView() {
        binding.testsRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.testsRecyclerView.adapter = testAdapter



        testAdapter.submitList(tests)
    }
}