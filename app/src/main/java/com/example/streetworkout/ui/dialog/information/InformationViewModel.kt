package com.example.streetworkout.ui.dialog.information

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetworkout.base.RxViewModel
import com.example.streetworkout.data.repository.UserRepository
import com.example.streetworkout.data.resource.entity.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers

class InformationViewModel(private val userRepository: UserRepository) : RxViewModel() {
    private val _user = MutableLiveData<User>()
    val user: LiveData<User>
        get() = _user

    init {
        userRepository.getInformationUser()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _user.value = it
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }

    fun updateUserInformation(user: User) {
        userRepository.updateUser(user)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addTo(disposables)
    }
}
