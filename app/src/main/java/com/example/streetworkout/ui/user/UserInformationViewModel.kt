package com.example.streetworkout.ui.user

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetworkout.base.RxViewModel
import com.example.streetworkout.data.repository.UserRepository
import com.example.streetworkout.data.resource.entity.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.addTo
import io.reactivex.rxjava3.schedulers.Schedulers.io

class UserInformationViewModel(private val userRepository: UserRepository) : RxViewModel() {
    private val _existUser = MutableLiveData<Int>()
    val existUser: LiveData<Int>
        get() = _existUser

    fun addUser(user: User) {
        userRepository.addUser(user)
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()
            .addTo(disposables)
    }

    fun checkUserExist() {
        userRepository.userCount()
            .subscribeOn(io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                if (it > 0) _existUser.value = it
            }, {
                _error.value = it.message.toString()
            })
            .addTo(disposables)
    }
}
