package com.example.streetworkout.ui.training

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streetworkout.data.model.Exercise

class ShareTrainingViewModel : ViewModel() {
    val exercises = MutableLiveData<List<Exercise>>()
    val position = MutableLiveData<Int>()

    fun setExercises(exercises: List<Exercise>) {
        this.exercises.value = exercises
    }

    fun setPosition(position: Int) {
        this.position.value = position
    }
}
