package com.example.probabilitytheorytests.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.probabilitytheorytests.TestResult
import com.example.probabilitytheorytests.databinding.TestResultItemBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TestResultAdapter : ListAdapter<TestResult, TestResultAdapter.TestResultViewHolder>(TestResultDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestResultViewHolder {
        val binding = TestResultItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TestResultViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TestResultViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class TestResultViewHolder(private val binding: TestResultItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(testResult: TestResult) {
            binding.testTitleTextView.text = testResult.testName
            binding.testQuestionsCountTextView.text = "Правильных ответов: ${testResult.correctAnswers} из ${testResult.totalQuestions}"
        }
    }

    class TestResultDiffCallback : DiffUtil.ItemCallback<TestResult>() {
        override fun areItemsTheSame(oldItem: TestResult, newItem: TestResult): Boolean {
            return oldItem.testName == newItem.testName
        }

        override fun areContentsTheSame(oldItem: TestResult, newItem: TestResult): Boolean {
            return oldItem == newItem
        }
    }
}