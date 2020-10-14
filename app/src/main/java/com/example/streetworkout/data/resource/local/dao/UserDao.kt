package com.example.streetworkout.data.resource.local.dao

import androidx.room.*
import com.example.streetworkout.data.resource.entity.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {
    @Query("SELECT COUNT(*) from user")
    fun userCount() : Single<Int>

    @Query("SELECT * FROM user ORDER BY id LIMIT 1")
    fun getInformationUser(): Flowable<User>

    @Insert
    fun addUser(user: User): Completable

    @Update
    fun updateUser(user: User): Completable

    @Query("DELETE FROM user")
    fun deleteTableUser(): Completable
}
