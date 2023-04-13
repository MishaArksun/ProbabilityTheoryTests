package com.example.probabilitytheorytests

import com.example.probabilitytheorytests.data.Question
import com.example.probabilitytheorytests.data.Test

object Repository {
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
        Test(
            testName = "Условная вероятность и независимость событий",
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
            testName = "Теорема Байеса",
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

    fun getTests(): List<Test> {
        return tests
    }
}