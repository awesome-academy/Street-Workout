package com.example.streetworkout.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(
    binding: ViewDataBinding,
    click: (T, Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    protected abstract val itemData: T?

    init {
        binding.root.setOnClickListener {
            itemData?.let {
                click(it, adapterPosition)
            }
        }
    }

    abstract fun bindData(item: T)
}
