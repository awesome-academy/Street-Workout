package com.example.streetworkout.data.model

import androidx.recyclerview.widget.DiffUtil
import com.google.gson.annotations.SerializedName

data class Exercise(
    @SerializedName("id_exercise")
    var idExercise: Int,
    @SerializedName("title")
    var title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("time")
    val time: String,
    @SerializedName("link_youtube")
    var linkYoutube: String,
    @SerializedName("id")
    var id: Int
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Exercise>() {
            override fun areItemsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Exercise, newItem: Exercise): Boolean {
                return oldItem == newItem
            }
        }
    }
}
