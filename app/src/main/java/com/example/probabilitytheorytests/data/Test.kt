package com.example.probabilitytheorytests.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Test(
    val testName: String,
    val questions: List<Question>
):Parcelable