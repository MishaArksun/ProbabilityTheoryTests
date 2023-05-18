package com.example.probabilitytheorytests.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.probabilitytheorytests.data.Test
import com.example.probabilitytheorytests.databinding.TestItemBinding

class TestAdapter(private val onTestClickListener: (Test) -> Unit) :
    ListAdapter<Test, TestAdapter.TestViewHolder>(TestDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestViewHolder {
        val binding = TestItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TestViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        val test = getItem(position)
        holder.binding.testTitleTextView.text = test.testName
        holder.binding.testQuestionsCountTextView.text = "Вопросов: ${test.questionIds?.size ?: 0}"
        holder.itemView.setOnClickListener { onTestClickListener(test) }
    }

    class TestViewHolder(val binding: TestItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    class TestDiffCallback : DiffUtil.ItemCallback<Test>() {
        override fun areItemsTheSame(oldItem: Test, newItem: Test): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Test, newItem: Test): Boolean {
            return oldItem == newItem
        }
    }
}