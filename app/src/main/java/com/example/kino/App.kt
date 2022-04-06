package com.example.kino

import android.app.Application
import com.example.kino.features.di.appModule
import com.example.kino.features.di.dataModule
import com.example.kino.features.di.domainModule
import com.example.kino.features.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            modules(listOf(appModule, networkModule, domainModule, dataModule))
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}