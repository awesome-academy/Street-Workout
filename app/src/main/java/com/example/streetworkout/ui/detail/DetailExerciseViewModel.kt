package com.example.streetworkout.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetworkout.base.RxViewModel
import com.example.streetworkout.data.model.Exercise
import com.example.streetworkout.data.repository.CategoryRepository
import io.reactivex.rxjava3.kotlin.addTo
import java.util.*

class DetailExerciseViewModel(private val repository: CategoryRepository) : RxViewModel() {

    private val exercises = mutableListOf<Exercise>()

    private val _exercises = MutableLiveData<List<Exercise>>()
    val exercise: LiveData<List<Exercise>>
        get() = _exercises

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun getListExercise(id: Int) {
        repository.getListExercise(id)
            .subscribe({
                if (exercises.isEmpty()) {
                    _exercises.postValue(it)
                    exercises.addAll(it)
                } else {
                    _exercises.postValue(exercises)
                }
            }, {
                _error.postValue(it.message.toString())
            })
            .addTo(disposables)
    }

    fun onMove(fromPosition: Int, toPosition: Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(exercises, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(exercises, i, i - 1)
            }
        }
        _exercises.value = exercises
    }
}
