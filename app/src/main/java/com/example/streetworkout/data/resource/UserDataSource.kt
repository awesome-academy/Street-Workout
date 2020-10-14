package com.example.streetworkout.data.resource

import com.example.streetworkout.data.resource.entity.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

interface UserDataSource {
    interface Local {
        fun userCount(): Single<Int>
        fun getInformationUser(): Flowable<User>
        fun addUser(user: User): Completable
        fun updateUser(user: User): Completable
        fun deleteTableUser(): Completable
    }
}
