package com.example.kino.features.di

import com.example.kino.App
import com.example.kino.features.content.data.api.MovieClient
import com.example.kino.features.content.data.datasource.ContentRemoteDataSource
import com.example.kino.features.content.data.datasource.ContentRemoteDataSourceImpl
import com.example.kino.features.content.data.repository.ContentRepositoryImpl
import com.example.kino.features.content.domain.repository.ContentRepository
import org.koin.dsl.module
import retrofit2.Retrofit


val dataModule = module {


    single<ContentRemoteDataSource> { ContentRemoteDataSourceImpl(api = get()) }
    single<ContentRepository> { ContentRepositoryImpl(remote = get(), context = get()) }
}