package com.example.streetworkout.data.resource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.streetworkout.data.resource.entity.Training
import com.example.streetworkout.data.resource.entity.User
import com.example.streetworkout.data.resource.local.dao.TrainingDao
import com.example.streetworkout.data.resource.local.dao.UserDao

@Database(entities = [Training::class, User::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trainingDao(): TrainingDao
    abstract fun userDao(): UserDao
}
