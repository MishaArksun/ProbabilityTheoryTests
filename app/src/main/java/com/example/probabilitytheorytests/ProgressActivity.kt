package com.example.probabilitytheorytests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.probabilitytheorytests.data.Test
import com.example.probabilitytheorytests.databinding.ActivityProgressBinding

class ProgressActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProgressBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProgressBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Получение списка тестов из Intent
        val tests = intent.getParcelableArrayExtra("tests")?.map { it as Test } ?: listOf()

        // Обновление totalQuestionsTextView
        val totalQuestions = tests.sumOf { it.questions.size }
        binding.totalQuestionsTextView.text = "Всего вопросов: $totalQuestions"
    }

    private fun updateUI(tests: Array<Test>) {
        // Здесь вы можете использовать данные из `tests` для обновления пользовательского интерфейса
        // Например, вы можете отобразить общий прогресс или количество пройденных вопросов
        val totalQuestions = tests.sumOf { it.questions.size }
        val totalCorrectAnswers =
            tests.sumOf { it.questions.count { question -> question.isAnswerCorrect } }

        binding.totalQuestionsTextView.text = "Всего вопросов: $totalQuestions"
        binding.correctAnswersTextView.text = "Правильных ответов: $totalCorrectAnswers"
    }
}