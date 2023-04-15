package com.example.probabilitytheorytests

import android.content.Context
import com.example.probabilitytheorytests.data.Category
import com.example.probabilitytheorytests.data.Question
import com.example.probabilitytheorytests.data.Test
import com.example.probabilitytheorytests.data.UserTestResult
import com.google.gson.Gson

object Repository {
    private val tests = listOf(
        Test(
            id = 1,
            testName = "Тест 1",
            categoryId = 1,
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
            id = 2,
            testName = "Тест 2",
            categoryId = 1,
            questions = listOf(
                Question(
                    question = "Что такое случайное событие?",
                    options = listOf("Событие, которое может или не может произойти", "Событие, которое происходит в определенное время", "Событие, которое происходит случайным образом", "Событие, которое происходит без какой-либо причины"),
                    correctAnswerIndex = 2
                ),
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
        Test(
            id = 3,
            testName = "Тест 3",
            categoryId = 1,
            questions = listOf(
                Question(
                    question = "Как найти вероятность суммы двух несовместных событий?",
                    options = listOf("P(A ∪ B) = P(A) + P(B)", "P(A ∪ B) = P(A) * P(B)", "P(A ∪ B) = P(A) - P(B)", "P(A ∪ B) = P(A) / P(B)"),
                    correctAnswerIndex = 0
                ),
                Question(
                    question = "Как найти вероятность пересечения двух независимых событий?",
                    options = listOf("P(A ∩ B) = P(A) * P(B)", "P(A ∩ B) = P(A) + P(B)", "P(A ∩ B) = P(A) - P(B)", "P(A ∩ B) = P(A) / P(B)"),
                    correctAnswerIndex = 0
                ),
                Question(
                    question = "Как найти вероятность объединения двух независимых событий?",
                    options = listOf("P(A ∪ B) = P(A) + P(B) - P(A) * P(B)", "P(A ∪ B) = P(A) * P(B)", "P(A ∪ B) = P(A) + P(B)", "P(A ∪ B) = P(A) - P(B)"),
                    correctAnswerIndex = 0
                )
            )
        ),
        Test(
            id = 4,
            testName = "Тест 4",
            categoryId = 1,
            questions = listOf(
                Question(
                    question = "Какова средняя вероятность успеха в биномиальном распределении?",
                    options = listOf("np", "n * (1 - p)", "p / n", "n / p"),
                    correctAnswerIndex = 0
                ),
                Question(
                    question = "Каково стандартное отклонение биномиального распределения?",
                    options = listOf("√(np(1 - p))", "np(1 - p)", "n(1 - p)", "n * p"),
                    correctAnswerIndex = 0
                )
            )
        ),
        Test(
            id = 5,
            testName = "Тест 1",
            categoryId = 2,
            questions = listOf(
                Question(
                    question = "Что такое условная вероятность?",
                    options = listOf("Вероятность события при условии, что произошло другое событие", "Вероятность события при условии, что не произошло другое событие", "Вероятность совместных событий", "Вероятность независимых событий"),
                    correctAnswerIndex = 0
                ),
                Question(
                    question = "Каково определение независимых событий?",
                    options = listOf("P(A ∩ B) = P(A) * P(B)", "P(A ∪ B) = P(A) + P(B)", "P(A | B) = P(A) + P(B)", "P(A | B) = P(A) * P(B)"),
                    correctAnswerIndex = 0
                ),
                Question(
                    question = "Если события A и B независимы, что можно сказать о P(A | B)?",
                    options = listOf("P(A | B) = P(A)", "P(A | B) = P(B)", "P(A | B) = P(A) + P(B)", "P(A | B) = P(A) * P(B)"),
                    correctAnswerIndex = 0
                )
            )
        ),
        Test(
            id = 6,
            testName = "Тест 2",
            categoryId = 2,
            questions = listOf(
                Question(
                    question = "Что такое параметр λ в распределении Пуассона?",
                    options = listOf("Среднее количество событий в единицу времени", "Вероятность события", "Стандартное отклонение", "Количество испытаний"),
                    correctAnswerIndex = 0
                ),
                Question(
                    question = "Как найти вероятность k событий в распределении Пуассона?",
                    options = listOf("(λ^k * e^(-λ)) / k!", "λ * e^(-λ)", "e^(λ - k)", "(λ * e^(-λ)) / k!"),
                    correctAnswerIndex = 0
                ),
                Question(
                    question = "Что является средним значением и дисперсией для распределения Пуассона?",
                    options = listOf("Среднее: λ, дисперсия: λ", "Среднее: λ, дисперсия: 1/λ", "Среднее: e^λ, дисперсия: λ", "Среднее: 1/λ, дисперсия: λ"),
                    correctAnswerIndex = 0
                )
            )
        ),
        Test(
            id = 7,
            testName = "Тест 3",
            categoryId = 2,
            questions = listOf(
                Question(
                    question = "Какое событие представляет распределение Бернулли?",
                    options = listOf("Однократное испытание с двумя возможными исходами", "Несколько испытаний с двумя возможными исходами", "Однократное испытание с множеством возможных исходов", "Несколько испытаний с множеством возможных исходов"),
                    correctAnswerIndex = 0
                ),
                Question(
                    question = "Каковы вероятности успеха (p) и неудачи (q) в распределении Бернулли?",
                    options = listOf("p = 1 - q, q = 1 - p", "p = q", "p = 1 / q, q = 1 / p", "p = q / (1 - q), q = p / (1 - p)"),
                    correctAnswerIndex = 0
                ),
                Question(
                    question = "Каково среднее значение и дисперсия для распределения Бернулли?",
                    options = listOf("Среднее: p, дисперсия: pq", "Среднее: q, дисперсия: pq", "Среднее: p, дисперсия: p^2", "Среднее: q, дисперсия: q^2"),
                    correctAnswerIndex = 0
                )
            )
        ),
        Test(
            id = 8,
            testName = "Тест 1",
            categoryId = 3,
            questions = listOf(
                Question(
                    question = "Что такое теорема Байеса?",
                    options = listOf("Формула для расчета условных вероятностей", "Формула для расчета совместных вероятностей", "Формула для расчета независимых вероятностей", "Формула для расчета вероятностей противоположных событий"),
                    correctAnswerIndex = 0
                ),
                Question(
                    question = "В каких ситуациях теорема Байеса обычно используется?",
                    options = listOf("Расчет априорных вероятностей", "Расчет апостериорных вероятностей", "Расчет условных вероятностей", "Расчет совместных вероятностей"),
                    correctAnswerIndex = 1
                )
            )
        )
    )

    private val categories = listOf(
        Category(id = 1, name = "Основы вероятности"),
        Category(id = 2, name = "Условная вероятность"),
        Category(id = 3, name = "Теорема Байеса")
    )

    fun getTests(): List<Test> {
        return tests
    }

    fun getCategories(): List<Category> {
        return categories
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
}