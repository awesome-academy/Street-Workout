package com.example.streetworkout.data.repository

import com.example.streetworkout.data.resource.UserDataSource
import com.example.streetworkout.data.resource.entity.User
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

class UserRepository(private val local: UserDataSource.Local) : UserDataSource.Local {
    override fun userCount(): Single<Int> = local.userCount()

    override fun getInformationUser(): Flowable<User> = local.getInformationUser()

    override fun addUser(user: User): Completable = local.addUser(user)

    override fun updateUser(user: User): Completable = local.updateUser(user)

    override fun deleteTableUser(): Completable = local.deleteTableUser()
}
