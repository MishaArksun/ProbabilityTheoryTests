package com.example.probabilitytheorytests.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.probabilitytheorytests.R
import com.example.probabilitytheorytests.data.CategoryProgress

class CategoryProgressAdapter :
    ListAdapter<CategoryProgress, CategoryProgressAdapter.CategoryProgressViewHolder>(CategoryProgressDiffCallback()) {

    class CategoryProgressDiffCallback : DiffUtil.ItemCallback<CategoryProgress>() {
        override fun areItemsTheSame(oldItem: CategoryProgress, newItem: CategoryProgress): Boolean {
            return oldItem.categoryName == newItem.categoryName && oldItem.percentage == newItem.percentage
        }

        override fun areContentsTheSame(oldItem: CategoryProgress, newItem: CategoryProgress): Boolean {
            return oldItem == newItem
        }
    }

    class CategoryProgressViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val categoryNameTextView: TextView = itemView.findViewById(R.id.categoryNameTextView)
        val percentageTextView: TextView = itemView.findViewById(R.id.percentageTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryProgressViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.result_item, parent, false)
        return CategoryProgressViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryProgressViewHolder, position: Int) {
        val categoryProgress = getItem(position)
        holder.categoryNameTextView.text = categoryProgress.categoryName
        holder.percentageTextView.text = String.format("%.2f%%", categoryProgress.percentage * 100)
    }
}