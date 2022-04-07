package com.example.kino.features.content.data.datasource

import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.domain.repository.ContentRepository


interface ContentLocalDataSource : ContentRepository {
    fun saveToFavorite(item: Movie)

    fun removeFromFavorite(item: Movie)
}