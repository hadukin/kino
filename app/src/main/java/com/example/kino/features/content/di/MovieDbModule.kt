package com.example.kino.features.content.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.kino.features.content.data.api.MoviesDao
import com.example.kino.features.content.data.api.MoviesDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val movieDatabaseModule = module {

    fun provideDatabase(context: Context): MoviesDatabase {
        return Room.databaseBuilder(context, MoviesDatabase::class.java, "movies")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideCountriesDao(database: MoviesDatabase): MoviesDao {
        return database.movieDao
    }

    single { provideDatabase(get()) }
    single { provideCountriesDao(get()) }
}
