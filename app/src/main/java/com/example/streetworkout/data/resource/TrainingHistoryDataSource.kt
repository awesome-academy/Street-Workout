package com.example.streetworkout.data.resource

import com.example.streetworkout.data.resource.entity.Training
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface TrainingHistoryDataSource {
    interface Local {
        fun getHistoryTraining(): Flowable<List<Training>>
        fun getTotalTimeTraining(): Single<Int>
        fun getTotalKcalTraining(): Single<Int>
        fun addTraining(training: Training): Completable
        fun deleteTableTraining(): Completable
    }
}
