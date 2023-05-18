package com.example.probabilitytheorytests.data
import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val id: Int, // добавляем ID вопроса
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val categoryId: Int, // добавляем ID категории
    var userAnswerIndex: Int? = null,
    var isAnsweredCorrectly: Boolean = false // добавляем флаг, который будет хранить, верно ли отвечено на вопрос
) : Parcelable {
    val isAnswerCorrect: Boolean
        get() = userAnswerIndex == correctAnswerIndex

    // обновляем статус isAnsweredCorrectly, когда пользователь выбирает ответ
    fun setUserAnswer(userAnswerIndex: Int) {
        this.userAnswerIndex = userAnswerIndex
        this.isAnsweredCorrectly = isAnswerCorrect
    }
}