package com.example.streetworkout.data.model

import com.google.gson.annotations.SerializedName

data class Category (
    @SerializedName("challenge")
    val challenge: List<GroupMuscle>,
    @SerializedName("beginner")
    val beginner: List<GroupMuscle>,
    @SerializedName("normal")
    val normal: List<GroupMuscle>,
    @SerializedName("advance")
    val advance: List<GroupMuscle>
)
