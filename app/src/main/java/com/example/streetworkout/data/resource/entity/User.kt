package com.example.streetworkout.data.resource.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey val id: Int?,
    val sex: Int,
    val age: Int,
    val weight: Int,
    val height: Int,
    val bmi: Double
)
