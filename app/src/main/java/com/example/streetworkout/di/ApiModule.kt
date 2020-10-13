package com.example.streetworkout.di

import com.example.streetworkout.data.resource.remote.util.APIService
import org.koin.dsl.module
import retrofit2.Retrofit

val apiModule = module {
    single { get<Retrofit>().create(APIService::class.java) }
}
