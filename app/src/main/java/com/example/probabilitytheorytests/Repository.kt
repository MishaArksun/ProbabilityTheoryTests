package com.example.probabilitytheorytests

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.example.probabilitytheorytests.data.Category
import com.example.probabilitytheorytests.data.Question
import com.example.probabilitytheorytests.data.Test
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException

object Repository {
    private var tests: List<Test> = listOf()
    private var categories: List<Category> = listOf()
    private var questions: List<Question> = listOf()
    private val gson = Gson()
    private lateinit var sharedPreferences: SharedPreferences

    // Инициализация SharedPreferences, вызывайте этот метод в onCreate() вашего MainActivity
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences("questions_status", Context.MODE_PRIVATE)
    }

    fun getTests(context: Context): List<Test> {
        if (tests.isEmpty()) {
            questions = loadQuestionsFromAssets(context)
            tests = loadTestsFromAssets(context)
        }
        return tests
    }

    fun getCategories(context: Context): List<Category> {
        if (categories.isEmpty()) {
            categories = loadCategoriesFromAssets(context)
        }
        return categories
    }

    private fun loadTestsFromAssets(context: Context): List<Test> {
        val jsonFileName = "tests.json"
        val jsonFileString = getJsonDataFromAsset(context, jsonFileName)
        val listType = object : TypeToken<List<Test>>() {}.type
        return gson.fromJson(jsonFileString, listType) ?: listOf()
    }

    private fun loadCategoriesFromAssets(context: Context): List<Category> {
        val jsonFileName = "categories.json"
        val jsonFileString = getJsonDataFromAsset(context, jsonFileName)
        val listType = object : TypeToken<List<Category>>() {}.type
        return gson.fromJson(jsonFileString, listType) ?: listOf()
    }

    private fun loadQuestionsFromAssets(context: Context): List<Question> {
        val jsonFileName = "questions.json"
        val jsonFileString = getJsonDataFromAsset(context, jsonFileName)
        val listType = object : TypeToken<List<Question>>() {}.type
        val questions: List<Question> = gson.fromJson(jsonFileString, listType) ?: listOf()

        // Восстанавливаем выбранный пользователем ответ для каждого вопроса
        questions.forEach { question ->
            question.userAnswerIndex = sharedPreferences.getInt("${question.id}_answer", -1).let { if (it != -1) it else null }
        }

        return questions
    }

    private fun clearAnswers(){
        questions.forEach { question ->
            question.userAnswerIndex = null
            question.isAnsweredCorrectly = false
        }
    }

    private fun getJsonDataFromAsset(context: Context, fileName: String): String {
        val jsonString: String
        try {
            jsonString = context.assets.open(fileName).bufferedReader().use { it.readText() }
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return ""
        }
        return jsonString
    }

    fun getTestsByCategoryId(categoryId: Int): List<Test> {
        return tests.filter { it.categoryId == categoryId }
    }

    fun getAllCategories(): List<Category> {
        return categories
    }
    fun getTestsCount(): Int {
        return tests.size
    }

    fun getQuestionsByCategory(categoryId: Int): List<Question> {
        return questions.filter { it.categoryId == categoryId }
    }

    // Обновление статуса вопроса и сохранение в SharedPreferences
    fun updateQuestionStatus(questionId: Int, isCorrect: Boolean, selectedAnswerIndex: Int) {
        val question = questions.find { it.id == questionId }
        question?.isAnsweredCorrectly = isCorrect
        question?.userAnswerIndex = selectedAnswerIndex

        val editor = sharedPreferences.edit()
        editor.putBoolean(questionId.toString(), isCorrect)
        editor.putInt("${questionId}_answer", selectedAnswerIndex)
        editor.apply()
    }
    // Загрузка статуса вопроса из SharedPreferences при запуске приложения
    fun loadQuestionsStatus() {
        questions.forEach { question ->
            question.isAnsweredCorrectly = sharedPreferences.getBoolean(question.id.toString(), false)
            question.userAnswerIndex = sharedPreferences.getInt("${question.id}_answer", -1)
        }
    }
    fun getTestById(testId: Int): Test? {
        return tests.find { it.id == testId }
    }
    fun getQuestionsByTest(testId: Int): List<Question> {
        val test = getTestById(testId)
        return test?.questionIds?.mapNotNull { id -> questions.find { it.id == id } } ?: emptyList()
    }
    fun getCategoryStatistics(categoryId: Int): Pair<Int, Int> {
        val categoryQuestions = questions.filter { it.categoryId == categoryId }
        val correctAnswers = categoryQuestions.count { it.isAnsweredCorrectly }
        return correctAnswers to categoryQuestions.size
    }

    fun getRecommendedTest(): Test {
        val incorrectQuestionsPerCategory = categories.map { category ->
            questions.filter { it.categoryId == category.id && !it.isAnsweredCorrectly }.shuffled().take(1)
        }.flatten()

        return Test(
            id = (tests.maxByOrNull { it.id }?.id ?: 0) + 1,  // New ID for the test
            testName = "Recommended Test",
            categoryId = 0,  // No specific category for recommended test
            questionIds = incorrectQuestionsPerCategory.map { it.id }
        )
    }
    fun getRecommendedQuestions(): List<Question> {
        val incorrectQuestionsPerCategory = categories.map { category ->
            questions.filter { it.categoryId == category.id && !it.isAnsweredCorrectly }.shuffled().take(1)
        }.flatten()

        return incorrectQuestionsPerCategory
    }

    fun resetTestResults() {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.apply()
        clearAnswers()
    }
}