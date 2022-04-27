package com.example.kino.features.content.data.api

import androidx.room.*
import com.example.kino.features.content.data.models.Schedule

@Dao
interface ScheduleDao {
    @Query("SELECT * FROM schedule")
    fun getAll(): List<Schedule>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg schedule: Schedule)

    @Delete
    fun delete(schedule: Schedule)
}

@Database(entities = [Schedule::class], version = 1)
abstract class SchedulesDatabase : RoomDatabase() {
    abstract fun scheduleDao(): ScheduleDao
}

