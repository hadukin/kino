package com.example.kino.features.content.di

import com.example.kino.features.content.data.datasource.ContentRemoteDataSource
import com.example.kino.features.content.data.datasource.ContentRemoteDataSourceImpl
import com.example.kino.features.content.data.repository.ContentRepositoryImpl
import com.example.kino.features.content.domain.repository.ContentRepository
import org.koin.dsl.module


val movieDataModule = module {
    single<ContentRemoteDataSource> { ContentRemoteDataSourceImpl(api = get()) }
    single<ContentRepository> { ContentRepositoryImpl(remote = get(), context = get()) }
}