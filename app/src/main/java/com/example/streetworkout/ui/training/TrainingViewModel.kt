package com.example.streetworkout.ui.training

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetworkout.base.RxViewModel
import com.example.streetworkout.data.model.Exercise
import com.example.streetworkout.data.repository.TrainingHistoryRepository
import com.example.streetworkout.data.repository.UserRepository
import com.example.streetworkout.data.resource.entity.Training
import com.example.streetworkout.util.PatternConst.PATTERN_DOUBLE
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers
import java.text.DecimalFormat
import java.util.concurrent.TimeUnit

class TrainingViewModel(
    private val trainingRepository: TrainingHistoryRepository,
    private val userRepository: UserRepository
) : RxViewModel() {

    private val exercises = mutableListOf<Exercise>()
    private var position = 0
    private var timeExercise = 0L
    private var timeTraining = 0L
    private var weightUser = 0

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

    private val _totalKcalTraining = MutableLiveData<Double>()
    val totalKcalTraining: LiveData<Double>
        get() = _totalKcalTraining

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
            })
            .addTo(disposables)
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
            })
            .addTo(disposables)
    }

    fun resumeStream() {
        if (timeExercise != 0L) countDownTimerExercise(timeExercise)
        if (timeTraining != 0L) startTimeTraining(timeTraining)
    }

    fun stopStream() {
        disposables.clear()
    }

    fun finishTraining(level: Double) {
        calculatorKcal(level)
        _totalTimeTraining.value = timeTraining.toInt()
        _totalTimeTraining.value = null
    }

    fun getWeightUser() {
        userRepository.getInformationUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                weightUser = it.weight
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }

    fun addHistoryTraining(training: Training) {
        trainingRepository.addTraining(training)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
            }, {
                _error.postValue(it.message.toString())
            })
            .addTo(disposables)
    }

    private fun calculatorKcal(level: Double) {
        val recipe =
            timeTraining.toDouble().div(SIXTY).times(THREE_POINT_FIVE).times(level).times(weightUser)
        val totalKcal = recipe.div(ONE_THOUSAND).times(FIVE)
        _totalKcalTraining.value =
            DecimalFormat(PATTERN_DOUBLE).format(totalKcal).replace(",", ".").toDouble()
    }

    companion object {
        private const val FIVE = 5
        private const val SIXTY = 60
        private const val ONE_THOUSAND = 1000
        private const val THREE_POINT_FIVE = 3.5
    }
}
