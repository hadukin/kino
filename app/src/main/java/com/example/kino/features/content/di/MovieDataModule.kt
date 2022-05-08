package com.example.kino.features.content.di

import com.example.kino.features.content.data.datasource.content.ContentLocalDataSource
import com.example.kino.features.content.data.datasource.content.ContentLocalDataSourceImpl
import com.example.kino.features.content.data.datasource.content.ContentRemoteDataSource
import com.example.kino.features.content.data.datasource.content.ContentRemoteDataSourceImpl
import com.example.kino.features.content.data.datasource.schedule.ScheduleLocalDataSource
import com.example.kino.features.content.data.datasource.schedule.ScheduleLocalDataSourceImpl
import com.example.kino.features.content.data.repository.ContentRepositoryImpl
import com.example.kino.features.content.data.repository.ScheduleRepositoryImpl
import com.example.kino.features.content.domain.repository.ContentRepository
import com.example.kino.features.content.domain.repository.ScheduleRepository
import org.koin.dsl.module

val movieDataModule = module {
    // Movie
    single<ContentRemoteDataSource> { ContentRemoteDataSourceImpl(api = get()) }
    single<ContentLocalDataSource> { ContentLocalDataSourceImpl(dao = get()) }
    single<ContentRepository> {
        ContentRepositoryImpl(
            remote = get(),
            context = get(),
            local = get()
        )
    }

    // Schedule
    single<ScheduleLocalDataSource> { ScheduleLocalDataSourceImpl(dao = get()) }
    single<ScheduleRepository> { ScheduleRepositoryImpl(local = get()) }
}