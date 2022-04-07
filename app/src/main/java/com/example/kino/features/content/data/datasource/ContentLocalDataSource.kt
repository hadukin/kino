package com.example.kino.features.content.data.datasource

import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.domain.repository.ContentRepository


interface ContentLocalDataSource : ContentRepository {
    fun saveAll(content: List<Movie>)
}