package com.example.streetworkout.ui.training

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetworkout.base.RxViewModel
import com.example.streetworkout.data.model.Exercise
import com.example.streetworkout.data.repository.TrainingHistoryRepository
import com.example.streetworkout.data.resource.entity.Training
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class TrainingViewModel(private val trainingRepository: TrainingHistoryRepository) : RxViewModel() {

    private val exercises = mutableListOf<Exercise>()
    private var position = 0
    private var timeExercise = 0L
    private var timeTraining = 0L

    private val _totalTimeTraining = MutableLiveData<Int>()
    val totalTimeTraining: LiveData<Int>
        get() = _totalTimeTraining

    private val _exercise = MutableLiveData<Exercise>()
    val exercise: LiveData<Exercise>
        get() = _exercise

    private val _countDownTimeExercise = MutableLiveData<Int>()
    val countDownTimeExercise: LiveData<Int>
        get() = _countDownTimeExercise

    private val _isExistExercise = MutableLiveData<Boolean>()
    val isExistExercise: LiveData<Boolean>
        get() = _isExistExercise

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun setListExercise(exercises: List<Exercise>) {
        if (this.exercises.isEmpty()) this.exercises.addAll(exercises)
    }

    fun setPosition(position: Int) {
        this.position = position
        _exercise.value = exercises[position]
    }

    fun openFragment() {
        _isExistExercise.value = position < exercises.size - 1
        _isExistExercise.value = null
    }

    fun startTimeTraining(long: Long) {
        Observable.interval(1, TimeUnit.SECONDS)
            .map { long + it }
            .subscribe({
                timeTraining = it
            }, {
                _error.postValue(it.message.toString())
            }).addTo(disposables)
    }

    fun countDownTimerExercise(long: Long) {
        Observable.interval(1, TimeUnit.SECONDS)
            .take(long + 1)
            .map { long - it }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _countDownTimeExercise.value = it.toInt()
                timeExercise = it
            }, {
                _error.postValue(it.message.toString())
            }, {
                openFragment()
            }).addTo(disposables)
    }

    fun resumeStream() {
        if (timeExercise != 0L) countDownTimerExercise(timeExercise)
        if (timeTraining != 0L) startTimeTraining(timeTraining)
    }

    fun stopStream() {
        disposables.clear()
    }

    fun finishTraining() {
        _totalTimeTraining.value = timeTraining.toInt()
        _totalTimeTraining.value = null
    }

    fun addHistoryTraining(training: Training) {
        trainingRepository.addTraining(training)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            }, {
                _error.postValue(it.message.toString())
            })
    }
}
