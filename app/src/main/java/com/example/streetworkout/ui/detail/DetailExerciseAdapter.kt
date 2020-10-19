package com.example.streetworkout.ui.detail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.streetworkout.R
import com.example.streetworkout.base.BaseAdapter
import com.example.streetworkout.data.model.Exercise

class DetailExerciseAdapter(
    private val onItemClick: (Exercise, Int) -> Unit,
    private val onImageTouch: (DetailExerciseViewHolder) -> Unit
) : BaseAdapter<Exercise, DetailExerciseViewHolder>(Exercise.diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailExerciseViewHolder =
        DetailExerciseViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recyclerview_detail_exercise,
                parent,
                false
            ),
            onItemClick,
            onImageTouch
        )
}
