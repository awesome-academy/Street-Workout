package com.example.streetworkout.di

import androidx.room.Room
import com.example.streetworkout.data.repository.CategoryRepository
import com.example.streetworkout.data.repository.TrainingHistoryRepository
import com.example.streetworkout.data.repository.UserRepository
import com.example.streetworkout.data.resource.CategoryDataSource
import com.example.streetworkout.data.resource.TrainingHistoryDataSource
import com.example.streetworkout.data.resource.UserDataSource
import com.example.streetworkout.data.resource.local.CategoryLocalDataSource
import com.example.streetworkout.data.resource.local.TrainingLocalDataSource
import com.example.streetworkout.data.resource.local.UserLocalDataSource
import com.example.streetworkout.data.resource.local.db.AppDatabase
import com.example.streetworkout.data.resource.remote.CategoryRemoteDataSource
import com.example.streetworkout.util.DatabaseConst.DB_NAME
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dbModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            DB_NAME
        ).build()
    }

    single { get<AppDatabase>().userDao() }
    single { get<AppDatabase>().trainingDao() }
}

val repoTrainingModule = module {
    single<TrainingHistoryDataSource.Local> { TrainingLocalDataSource(get()) }

    single { TrainingHistoryRepository(get()) }
}

val repoUserModule = module {
    single<UserDataSource.Local> { UserLocalDataSource(get()) }

    single { UserRepository(get()) }
}

val repoCategoryModule = module {
    single<CategoryDataSource.Local> { CategoryLocalDataSource() }
    single<CategoryDataSource.Remote> { CategoryRemoteDataSource(get()) }

    single { CategoryRepository(get(), get()) }
}
