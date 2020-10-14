package com.example.streetworkout.data.resource.local.dao

import androidx.room.*
import com.example.streetworkout.data.resource.entity.Training
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable

@Dao
interface TrainingDao {
    @Query("SELECT * FROM training")
    fun getHistoryTraining(): Flowable<List<Training>>

    @Insert
    fun addTraining(training: Training): Completable

    @Query("DELETE FROM training")
    fun deleteTableTraining(): Completable
}
