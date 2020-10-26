package com.example.streetworkout.data.resource.local

import com.example.streetworkout.data.resource.TrainingHistoryDataSource
import com.example.streetworkout.data.resource.entity.Training
import com.example.streetworkout.data.resource.local.dao.TrainingDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

class TrainingLocalDataSource(
    private val trainingDao: TrainingDao
) : TrainingHistoryDataSource.Local {
    override fun getHistoryTraining(): Flowable<List<Training>> = trainingDao.getHistoryTraining()

    override fun getTotalTimeTraining(): Single<Int> = trainingDao.getTotalTimeTraining()

    override fun getTotalKcalTraining(): Single<Int> = trainingDao.getTotalKcalTraining()

    override fun addTraining(training: Training): Completable = trainingDao.addTraining(training)

    override fun deleteTableTraining(): Completable = trainingDao.deleteTableTraining()
}
