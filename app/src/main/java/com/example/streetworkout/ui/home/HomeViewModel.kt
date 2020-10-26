package com.example.streetworkout.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetworkout.base.RxViewModel
import com.example.streetworkout.data.model.Category
import com.example.streetworkout.data.repository.CategoryRepository
import com.example.streetworkout.data.repository.TrainingHistoryRepository
import com.example.streetworkout.data.resource.entity.Training
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.functions.Function3
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers.io

class HomeViewModel(
    private val categoryRepository: CategoryRepository,
    private val trainingHistoryRepository: TrainingHistoryRepository
) : RxViewModel() {

    private val _categories = MutableLiveData<Category>()
    val categories: LiveData<Category>
        get() = _categories

    private val _historyInformation = MutableLiveData<Triple<Int, Int, Int>>()
    val historyInformation: LiveData<Triple<Int, Int, Int>>
        get() = _historyInformation

    fun getData() {
        categoryRepository.getInformationHome()
            .subscribe({
                _categories.postValue(it)
            }, {
                _error.postValue(it.message.toString())
            })
            .addTo(disposables)
    }

    fun getTrainingHistoryInformation() {
        Observable.combineLatest(
            trainingHistoryRepository.getHistoryTraining().toObservable(),
            trainingHistoryRepository.getTotalKcalTraining().toObservable(),
            trainingHistoryRepository.getTotalTimeTraining().toObservable(),
            Function3<List<Training>, Int, Int, Triple<Int, Int, Int>> { training, totalKcal, totalTime ->
                Triple(training.size, totalKcal, totalTime)
            }
        )
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _historyInformation.value = it
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }
}
