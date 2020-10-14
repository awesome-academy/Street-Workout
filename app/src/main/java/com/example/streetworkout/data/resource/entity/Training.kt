package com.example.streetworkout.data.resource.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "training")
data class Training(
    @PrimaryKey val id: Int?,
    @ColumnInfo(name = "exercise_name") val name: String,
    @ColumnInfo(name = "image_preview") val image: Int,
    @ColumnInfo(name = "total_time") val totalTime: Int,
    @ColumnInfo(name = "total_kcal") val totalKcal: Int,
    @ColumnInfo(name = "date") val date: String
)
