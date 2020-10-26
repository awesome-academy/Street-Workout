package com.example.streetworkout.ui.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetworkout.base.RxViewModel
import com.example.streetworkout.data.repository.TrainingHistoryRepository
import com.example.streetworkout.data.resource.entity.Training
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers.io

class TrainingHistoryViewModel(private val repository: TrainingHistoryRepository) : RxViewModel() {

    private val _trainingHistory = MutableLiveData<List<Training>>()
    val trainingHistory: LiveData<List<Training>>
        get() = _trainingHistory

    init {
        repository.getHistoryTraining()
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _trainingHistory.value = it
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }
}
