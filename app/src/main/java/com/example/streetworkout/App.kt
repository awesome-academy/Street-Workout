package com.example.streetworkout

import android.app.Application
import com.example.streetworkout.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(listOf(
                apiModule,
                networkModule,
                dbModule,
                repoCategoryModule,
                repoTrainingModule,
                repoUserModule)
            )
        }
    }
}
