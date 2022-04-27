package com.example.kino.features.content.data.repository

import com.example.kino.features.content.data.datasource.schedule.ScheduleLocalDataSource
import com.example.kino.features.content.data.models.Schedule
import com.example.kino.features.content.domain.repository.ScheduleRepository

class ScheduleRepositoryImpl(private val local: ScheduleLocalDataSource) : ScheduleRepository {
    override suspend fun readAllSchedule() {
        local.readAllSchedule()
    }

    override suspend fun createSchedule(schedule: Schedule) {
        local.createSchedule(schedule)
    }

    override suspend fun deleteSchedule(schedule: Schedule) {
        local.deleteSchedule(schedule)
    }
}