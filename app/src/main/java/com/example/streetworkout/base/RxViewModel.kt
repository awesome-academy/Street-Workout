package com.example.streetworkout.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class RxViewModel : ViewModel() {
    protected val disposables = CompositeDisposable()

    protected val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }
}
