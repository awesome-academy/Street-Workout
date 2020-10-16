package com.example.streetworkout.ui.home

import androidx.databinding.library.baseAdapters.BR
import com.example.streetworkout.base.BaseViewHolder
import com.example.streetworkout.data.model.GroupMuscle
import com.example.streetworkout.databinding.ItemRecyclerviewMuscleGroupBinding

class HomeViewHolder(
    private val binding: ItemRecyclerviewMuscleGroupBinding,
    onClickItem: (GroupMuscle, Int) -> Unit
) : BaseViewHolder<GroupMuscle>(binding, onClickItem) {
    override val itemData: GroupMuscle?
        get() = binding.groupMuscle

    override fun bindData(item: GroupMuscle) {
        binding.setVariable(BR.groupMuscle, item)
        binding.executePendingBindings()
    }
}
