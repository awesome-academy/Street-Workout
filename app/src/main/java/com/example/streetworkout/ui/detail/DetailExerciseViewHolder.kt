package com.example.streetworkout.ui.detail

import android.view.MotionEvent
import androidx.databinding.library.baseAdapters.BR
import com.example.streetworkout.base.BaseViewHolder
import com.example.streetworkout.data.model.Exercise
import com.example.streetworkout.databinding.ItemRecyclerviewDetailExerciseBinding
import kotlinx.android.synthetic.main.item_recyclerview_detail_exercise.view.*

class DetailExerciseViewHolder(
    private val binding: ItemRecyclerviewDetailExerciseBinding,
    onItemClick: (Exercise, Int) -> Unit,
    onImageTouch: (DetailExerciseViewHolder) -> Unit
) : BaseViewHolder<Exercise>(binding, onItemClick) {

    init {
        binding.root.imageViewChange.setOnTouchListener { view, motionEvent ->
            if (motionEvent.actionMasked == MotionEvent.ACTION_DOWN) {
                view.performClick()
                onImageTouch(this)
            }
            return@setOnTouchListener true
        }
    }

    override val itemData: Exercise?
        get() = binding.exercise

    override fun bindData(item: Exercise) = with(binding) {
        setVariable(BR.exercise, item)
        executePendingBindings()
    }
}
