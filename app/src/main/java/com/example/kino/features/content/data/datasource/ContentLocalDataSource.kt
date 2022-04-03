package com.example.kino.features.content.data.datasource

import com.example.kino.features.content.domain.repository.ContentRepository
import com.example.kino.models.Movie

interface ContentLocalDataSource : ContentRepository {
    fun save(content: List<Movie>)
}