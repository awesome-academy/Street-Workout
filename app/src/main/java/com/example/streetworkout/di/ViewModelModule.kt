package com.example.streetworkout.di

import com.example.streetworkout.ui.training.ShareTrainingViewModel
import com.example.streetworkout.ui.detail.DetailExerciseViewModel
import com.example.streetworkout.ui.dialog.exercise.DialogPreviewExerciseViewModel
import com.example.streetworkout.ui.dialog.information.InformationViewModel
import com.example.streetworkout.ui.history.TrainingHistoryViewModel
import com.example.streetworkout.ui.home.HomeViewModel
import com.example.streetworkout.ui.rest.RestTimeViewModel
import com.example.streetworkout.ui.setting.SettingsViewModel
import com.example.streetworkout.ui.training.TrainingViewModel
import com.example.streetworkout.ui.user.UserInformationViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { UserInformationViewModel(get()) }
    viewModel { HomeViewModel(get(), get()) }
    viewModel { DetailExerciseViewModel(get()) }
    viewModel { DialogPreviewExerciseViewModel() }
    viewModel { ShareTrainingViewModel() }
    viewModel { TrainingViewModel(get(), get()) }
    viewModel { RestTimeViewModel() }
    viewModel { TrainingHistoryViewModel(get()) }
    viewModel { SettingsViewModel(get(), get()) }
    viewModel { InformationViewModel(get()) }
}
