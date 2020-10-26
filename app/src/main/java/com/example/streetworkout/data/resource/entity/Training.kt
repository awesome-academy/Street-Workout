package com.example.streetworkout.data.resource.entity

import androidx.recyclerview.widget.DiffUtil
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training")
data class Training(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "exercise_name") val name: String,
    @ColumnInfo(name = "image_preview") val image: Int,
    @ColumnInfo(name = "total_time") val totalTime: Int,
    @ColumnInfo(name = "total_kcal") val totalKcal: Double,
    @ColumnInfo(name = "date") val date: String,
    @ColumnInfo(name = "image") val imageExercise: String,
    @ColumnInfo(name = "id_exercise") val idExercise: Int,
    @ColumnInfo(name = "level") val level: Double
) {
    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<Training>() {
            override fun areItemsTheSame(oldItem: Training, newItem: Training): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Training, newItem: Training): Boolean {
                return oldItem == newItem
            }
        }
    }
}
