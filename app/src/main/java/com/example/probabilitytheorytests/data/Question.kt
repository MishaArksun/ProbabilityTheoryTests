package com.example.probabilitytheorytests.data
import android.os.Parcel
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Question(
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    var userAnswerIndex: Int? = null
): Parcelable {
    val isAnswerCorrect: Boolean
        get() = userAnswerIndex == correctAnswerIndex
}