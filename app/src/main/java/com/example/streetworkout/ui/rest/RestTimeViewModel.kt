package com.example.streetworkout.ui.rest

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetworkout.base.RxViewModel
import com.example.streetworkout.data.model.Exercise
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import java.util.concurrent.TimeUnit

class RestTimeViewModel : RxViewModel() {

    private val exercises = mutableListOf<Exercise>()
    private var position = 0
    private var currentPage = 0
    private var timeRest = 0L

    private val _exercise = MutableLiveData<Exercise>()
    val exercise: LiveData<Exercise>
        get() = _exercise

    private val _textCurrentPage = MutableLiveData<String>()
    val textCurrentPage: LiveData<String>
        get() = _textCurrentPage

    private val _countDownTimeRest = MutableLiveData<Int>()
    val countDownTimeRest: LiveData<Int>
        get() = _countDownTimeRest

    private val _isRunComplete = MutableLiveData<Boolean>()
    val isRunComplete: LiveData<Boolean>
        get() = _isRunComplete

    private val _currentPosition = MutableLiveData<Int>()
    val currentPosition: LiveData<Int>
        get() = _currentPosition

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun setListExercise(exercises: List<Exercise>) {
        this.exercises.addAll(exercises)
    }

    fun setCurrentPosition(position: Int) {
        currentPage = position
        currentPage += 2
        this.position = position
        if (position < exercises.size - 1) _exercise.value = exercises[++this.position]
        _textCurrentPage.value = "$currentPage/${exercises.size}"
    }

    fun countDownTimerRest(long: Long) {
        Observable.interval(1, TimeUnit.SECONDS)
            .take(long + 1)
            .map { long - it }
            .subscribe({
                _countDownTimeRest.postValue(it.toInt())
                timeRest = it
            }, {
                _error.postValue(it.message.toString())
            }, {
                _isRunComplete.postValue(true)
            }).addTo(disposables)
    }

    fun skipRestTimeTraining() {
        _currentPosition.value = position
    }

    fun updateRestTime() {
        disposables.clear()
        timeRest += 20
        _countDownTimeRest.value = timeRest.toInt()
        countDownTimerRest(timeRest)
    }
}
