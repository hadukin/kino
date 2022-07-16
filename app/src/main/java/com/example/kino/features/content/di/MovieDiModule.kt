package com.example.kino.features.content.di

import org.koin.dsl.module

val movieDiModule = module {
    includes(
        movieModule,
        movieNetworkModule,
        movieDomainModule,
        movieDataModule,
        movieDatabaseModule,
        moviePresentationModule,
    )
}