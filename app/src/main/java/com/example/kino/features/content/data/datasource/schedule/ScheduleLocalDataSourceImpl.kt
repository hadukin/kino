package com.example.kino.features.content.data.datasource.schedule

import com.example.kino.features.content.data.api.ScheduleDao
import com.example.kino.features.content.data.models.Schedule

class ScheduleLocalDataSourceImpl(private var dao: ScheduleDao) : ScheduleLocalDataSource {
    override suspend fun readAllSchedule() {
        dao.getAll()
    }

    override suspend fun createSchedule(schedule: Schedule) {
        dao.insert(schedule)
    }

    override suspend fun deleteSchedule(schedule: Schedule) {
        dao.delete(schedule)
    }
}