package com.example.kino.repository

import com.example.kino.models.Content

interface ContentRepository {
    suspend fun getContent(): List<Content>
}