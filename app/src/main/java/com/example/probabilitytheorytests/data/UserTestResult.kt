package com.example.probabilitytheorytests.data

data class UserTestResult(
    val testId: Int,
    val testName: String,
    val date: org.threeten.bp.LocalDateTime, // для хранения даты и времени прохождения теста
    val correctAnswers: Int,
    val totalQuestions: Int
)