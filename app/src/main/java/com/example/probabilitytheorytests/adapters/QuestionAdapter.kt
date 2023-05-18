package com.example.probabilitytheorytests.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.probabilitytheorytests.Repository
import com.example.probabilitytheorytests.data.Question
import com.example.probabilitytheorytests.databinding.QuestionItemBinding

class QuestionAdapter : ListAdapter<Question, QuestionAdapter.QuestionViewHolder>(QuestionDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        val binding = QuestionItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return QuestionViewHolder(binding)
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class QuestionViewHolder(private val binding: QuestionItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(question: Question) {
            binding.questionTextView.text = question.question
            binding.option1RadioButton.text = question.options[0]
            binding.option2RadioButton.text = question.options[1]
            binding.option3RadioButton.text = question.options[2]
            binding.option4RadioButton.text = question.options[3]
            // если добавляли больше вариантов ответа, привяжите их текст здесь

            // Очищаем предыдущий выбор перед установкой нового слушателя, чтобы избежать повторных вызовов
            binding.optionsRadioGroup.setOnCheckedChangeListener(null)
            binding.optionsRadioGroup.clearCheck()

            // Устанавливаем выбранный пользователем ответ, если он есть
            if (question.userAnswerIndex != null && question.userAnswerIndex != -1) {
                binding.optionsRadioGroup.check(binding.optionsRadioGroup.getChildAt(question.userAnswerIndex!!).id)
            }

            binding.optionsRadioGroup.setOnCheckedChangeListener { _, checkedId ->
                val selectedOptionIndex = when (checkedId) {
                    binding.option1RadioButton.id -> 0
                    binding.option2RadioButton.id -> 1
                    binding.option3RadioButton.id -> 2
                    binding.option4RadioButton.id -> 3
                    else -> null
                }
                question.userAnswerIndex = selectedOptionIndex

                // Обновление статуса ответа
                if (selectedOptionIndex == question.correctAnswerIndex) {
                    // Ответ верный
                    Repository.updateQuestionStatus(question.id, true, selectedOptionIndex ?: -1)
                } else {
                    // Ответ неверный
                    Repository.updateQuestionStatus(question.id, false, selectedOptionIndex ?: -1)
                }
            }
        }
    }

    class QuestionDiffCallback : DiffUtil.ItemCallback<Question>() {
        override fun areItemsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Question, newItem: Question): Boolean {
            return oldItem == newItem
        }
    }
}