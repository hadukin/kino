package com.example.kino.features.content.domain.repository

import com.example.kino.features.content.data.models.Schedule

interface ScheduleRepository {
    suspend fun readAllSchedule(): List<Schedule>
    suspend fun createSchedule(schedule: Schedule)
    suspend fun deleteSchedule(schedule: Schedule)
    suspend fun getScheduleById(filmId: Int): Schedule
}