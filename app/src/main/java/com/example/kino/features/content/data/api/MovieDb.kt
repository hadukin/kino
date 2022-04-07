package com.example.kino.features.content.data.api

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kino.features.content.data.models.Movie

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movie")
    fun getAll(): LiveData<Movie>

    @Insert
    fun insertAll(vararg users: Movie)

    @Delete
    fun delete(user: Movie)
}

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract val movieDao: MoviesDao
}

