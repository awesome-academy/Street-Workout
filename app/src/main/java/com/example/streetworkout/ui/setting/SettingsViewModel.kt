package com.example.streetworkout.ui.setting

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetworkout.base.RxViewModel
import com.example.streetworkout.data.repository.TrainingHistoryRepository
import com.example.streetworkout.data.repository.UserRepository
import com.example.streetworkout.data.resource.entity.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers.io

class SettingsViewModel(
    userRepository: UserRepository,
    private val trainingHistoryRepository: TrainingHistoryRepository
) : RxViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    private val _deleteSuccess = MutableLiveData<Boolean>()
    val deleteSuccess: LiveData<Boolean>
        get() = _deleteSuccess

    init {
        userRepository.getInformationUser()
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _user.value = it
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }

    fun resetTrainingHistory() {
        trainingHistoryRepository.deleteTableTraining()
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _deleteSuccess.value = true
                _deleteSuccess.value = false
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }
}
