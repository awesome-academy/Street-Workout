package com.example.streetworkout.ui.history

import com.example.streetworkout.BR
import com.example.streetworkout.base.BaseViewHolder
import com.example.streetworkout.data.resource.entity.Training
import com.example.streetworkout.databinding.ItemRecyclerviewHistoryTrainingBinding

class TrainingHistoryViewHolder(
    private val binding: ItemRecyclerviewHistoryTrainingBinding,
    onItemClick: (Training, Int) -> Unit
) : BaseViewHolder<Training>(binding, onItemClick) {
    override val itemData: Training?
        get() = binding.training

    override fun bindData(item: Training) = with(binding) {
        setVariable(BR.training, item)
        executePendingBindings()
    }
}
