package com.example.kino.features.content.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.kino.features.content.data.api.MoviesDao
import com.example.kino.features.content.data.api.MoviesDatabase
import com.example.kino.features.content.data.api.ScheduleDao
import com.example.kino.features.content.data.api.SchedulesDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module


val movieDatabaseModule = module {

    // Movie db
    fun provideMoviesDatabase(context: Context): MoviesDatabase {
        return Room.databaseBuilder(context, MoviesDatabase::class.java, "movies")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideMoviesDao(database: MoviesDatabase): MoviesDao {
        return database.movieDao()
    }

    single { provideMoviesDatabase(get()) }
    single { provideMoviesDao(get()) }


    // Schedule db
    fun provideScheduleDatabase(context: Context): SchedulesDatabase {
        return Room.databaseBuilder(context, SchedulesDatabase::class.java, "schedules")
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideScheduleDao(database: SchedulesDatabase): ScheduleDao {
        return database.scheduleDao()
    }

    single { provideScheduleDatabase(get()) }
    single { provideScheduleDao(get()) }

}
