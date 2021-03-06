package com.example.streetworkout.data.repository

import com.example.streetworkout.data.resource.TrainingHistoryDataSource
import com.example.streetworkout.data.resource.entity.Training
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

class TrainingHistoryRepository(
    private val local: TrainingHistoryDataSource.Local
) : TrainingHistoryDataSource.Local {
    override fun getHistoryTraining(): Flowable<List<Training>> = local.getHistoryTraining()

    override fun getTotalTimeTraining(): Single<Int> = local.getTotalTimeTraining()

    override fun getTotalKcalTraining(): Single<Int> = local.getTotalKcalTraining()

    override fun addTraining(training: Training): Completable = local.addTraining(training)

    override fun deleteTableTraining(): Completable = local.deleteTableTraining()
}
