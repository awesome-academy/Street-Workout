package com.example.streetworkout.data.resource.local.dao

import androidx.room.*
import com.example.streetworkout.data.resource.entity.Training
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface TrainingDao {
    @Query("SELECT * FROM training ORDER BY id DESC")
    fun getHistoryTraining(): Flowable<List<Training>>

    @Insert
    fun addTraining(training: Training): Completable

    @Query("DELETE FROM training")
    fun deleteTableTraining(): Completable

    @Query("SELECT SUM(total_time) FROM training")
    fun getTotalTimeTraining(): Single<Int>

    @Query("SELECT SUM(total_kcal) FROM training")
    fun getTotalKcalTraining(): Single<Int>
}
