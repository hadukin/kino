package com.example.kino.features.content.domain.repository

import com.example.kino.features.content.data.models.Schedule

interface ScheduleRepository {
    fun createSchedule(schedule: Schedule)
    fun deleteSchedule(schedule: Schedule)
}