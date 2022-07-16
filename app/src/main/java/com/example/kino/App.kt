package com.example.kino

import android.app.Application
import com.example.kino.core.di.coreDiModule
import com.example.kino.features.content.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.fragment.koin.fragmentFactory
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        instance = this

        startKoin {
            androidLogger(if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(this@App)
            fragmentFactory()
            modules(
                listOf(
                    coreDiModule,
                    movieDiModule,
                )
            )
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}