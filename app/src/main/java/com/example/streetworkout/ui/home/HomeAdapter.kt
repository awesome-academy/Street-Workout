package com.example.streetworkout.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.streetworkout.R
import com.example.streetworkout.base.BaseAdapter
import com.example.streetworkout.data.model.GroupMuscle

class HomeAdapter(
    private val onItemClick: (GroupMuscle, Int) -> Unit
) : BaseAdapter<GroupMuscle, HomeViewHolder>(GroupMuscle.diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_recyclerview_muscle_group,
                parent,
                false
            ),
            onItemClick
        )
    }
}
