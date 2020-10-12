package com.example.streetworkout.di

import com.example.streetworkout.data.repository.CategoryRepository
import com.example.streetworkout.data.resource.CategoryDataSource
import com.example.streetworkout.data.resource.local.CategoryLocalDataSource
import com.example.streetworkout.data.resource.remote.CategoryRemoteDataSource
import org.koin.dsl.module

val repoModule = module {
    single<CategoryDataSource.Local> { CategoryLocalDataSource() }
    single<CategoryDataSource.Remote> { CategoryRemoteDataSource(get()) }

    single { CategoryRepository(get(), get()) }
}
