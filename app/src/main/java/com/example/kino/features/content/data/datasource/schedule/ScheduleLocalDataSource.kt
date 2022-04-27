package com.example.kino.features.content.data.datasource.schedule

import com.example.kino.features.content.data.models.Movie
import com.example.kino.features.content.data.models.Schedule

interface ScheduleLocalDataSource {
    suspend fun readAllSchedule()
    suspend fun createSchedule(schedule: Schedule)
    suspend fun deleteSchedule(schedule: Schedule)
}