package com.example.streetworkout.data.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class GroupMuscle(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("level")
    val level: Int
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<GroupMuscle>() {
            override fun areItemsTheSame(oldItem: GroupMuscle, newItem: GroupMuscle): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: GroupMuscle, newItem: GroupMuscle): Boolean {
                return oldItem == newItem
            }
        }
    }
}
