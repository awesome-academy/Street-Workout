package com.example.streetworkout.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.streetworkout.R
import com.example.streetworkout.base.BaseAdapter
import com.example.streetworkout.data.resource.entity.Training

class TrainingHistoryAdapter(
    private val onItemClick: (Training, Int) -> Unit
) : BaseAdapter<Training, TrainingHistoryViewHolder>(Training.diffUtil) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrainingHistoryViewHolder {
        return TrainingHistoryViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recyclerview_history_training,
                parent,
                false
            ),
            onItemClick
        )
    }
}
