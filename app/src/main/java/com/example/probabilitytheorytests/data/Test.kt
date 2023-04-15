package com.example.probabilitytheorytests.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Test(
    val id: Int,
    val testName: String,
    val categoryId: Int,
    val questions: List<Question>
): Parcelable