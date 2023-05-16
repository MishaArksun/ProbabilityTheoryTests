package com.example.probabilitytheorytests

import android.content.Context
import com.example.probabilitytheorytests.data.Category
import com.example.probabilitytheorytests.data.Question
import com.example.probabilitytheorytests.data.Test
import com.example.probabilitytheorytests.data.UserTestResult
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.IOException
import java.io.InputStreamReader

object Repository {
    private var tests: List<Test> = listOf()
    private var categories: List<Category> = listOf()
    private val gson = Gson()

    fun getTests(context: Context): List<Test> {
        if (tests.isEmpty()) {
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

    fun getTestsByCategory(categoryId: Int): List<Test> {
        return tests.filter { it.categoryId == categoryId }
    }

    fun getAllCategories(): List<Category> {
        return categories
    }

    fun getLatestUserTestResultsByTestIds(context: Context, testIds: List<Int>): List<UserTestResult> {
        val sharedPreferences = context.getSharedPreferences("test_results", Context.MODE_PRIVATE)
        val gson = Gson()

        return sharedPreferences.all.entries
            .map { (_, value) ->
                val json = value as String
                gson.fromJson(json, UserTestResult::class.java)
            }
            .filter { it.testId in testIds }
    }

    fun getRecommendedTest(context: Context): Test {
        val userResults = getLatestUserTestResultsByTestIds(context, tests.map { it.id })

        return tests.minByOrNull { test ->
            val totalCorrectAnswers = userResults.find { it.testId == test.id }?.correctAnswers ?: 0
            val totalQuestions = test.questions.size * userResults.count { it.testId == test.id }
            if (totalQuestions == 0) 0.0 else totalCorrectAnswers.toDouble() / totalQuestions
        } ?: throw IllegalStateException("No tests available")
    }
}