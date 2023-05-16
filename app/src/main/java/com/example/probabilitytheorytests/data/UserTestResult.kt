package com.example.probabilitytheorytests.data

data class UserTestResult(
    val testId: Int,
    val testName: String,
    val date: org.threeten.bp.LocalDateTime,
    val correctAnswers: Int,
    val totalQuestions: Int
)