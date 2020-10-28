package com.example.streetworkout.ui.dialog.exercise

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.streetworkout.data.model.Exercise

class DialogPreviewExerciseViewModel : ViewModel() {

    private val exercises = mutableListOf<Exercise>()
    private var position = 0

    private val _exercise = MutableLiveData<Exercise>()
    val exercise: LiveData<Exercise>
        get() = _exercise

    private val _currentPage = MutableLiveData<Int>()
    val currentPage: LiveData<Int>
        get() = _currentPage

    fun nextExercise() {
        _exercise.value = exercises[++position]
        _currentPage.value = position
    }

    fun previousExercise() {
        _exercise.value = exercises[--position]
        _currentPage.value = position
    }

    fun setPosition(position: Int) {
        this.position = position
        _currentPage.value = position
    }

    fun setListExercise(exercises: Array<Exercise>) {
        this.exercises.addAll(exercises)
        _exercise.value = exercises[position]
    }
}
