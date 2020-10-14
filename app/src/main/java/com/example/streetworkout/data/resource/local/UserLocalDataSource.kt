package com.example.streetworkout.data.resource.local

import com.example.streetworkout.data.resource.entity.User
import com.example.streetworkout.data.resource.UserDataSource
import com.example.streetworkout.data.resource.local.dao.UserDao
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single

class UserLocalDataSource(private val userDao: UserDao) : UserDataSource.Local {
    override fun userCount(): Single<Int> = userDao.userCount()

    override fun getInformationUser(): Flowable<User> = userDao.getInformationUser()

    override fun addUser(user: User): Completable = userDao.addUser(user)

    override fun updateUser(user: User): Completable = userDao.updateUser(user)

    override fun deleteTableUser(): Completable = userDao.deleteTableUser()
}
