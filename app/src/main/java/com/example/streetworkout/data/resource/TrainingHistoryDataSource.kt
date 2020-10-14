package com.example.streetworkout.data.resource

import com.example.streetworkout.data.resource.entity.Training
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

interface TrainingHistoryDataSource {
    interface Local {
        fun getHistoryTraining(): Flowable<List<Training>>
        fun addTraining(training: Training): Completable
        fun deleteTableTraining(): Completable
    }
}
