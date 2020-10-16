package com.example.streetworkout.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.streetworkout.base.RxViewModel
import com.example.streetworkout.data.model.Category
import com.example.streetworkout.data.repository.CategoryRepository
import io.reactivex.rxjava3.kotlin.addTo

class HomeViewModel(private val categoryRepository: CategoryRepository) : RxViewModel() {

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _categories = MutableLiveData<Category>()
    val categories: LiveData<Category>
        get() = _categories

    fun getData() {
        categoryRepository.getInformationHome()
            .subscribe({
                _categories.postValue(it)
            }, {
                _error.postValue(it.message.toString())
            })
            .addTo(disposables)
    }
}
