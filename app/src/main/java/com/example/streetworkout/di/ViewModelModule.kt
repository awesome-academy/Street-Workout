package com.example.streetworkout.di

import com.example.streetworkout.ui.detail.DetailExerciseViewModel
import com.example.streetworkout.ui.dialog.DialogPreviewExerciseViewModel
import com.example.streetworkout.ui.home.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { DetailExerciseViewModel(get()) }
    viewModel { DialogPreviewExerciseViewModel() }
}
