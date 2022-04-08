package com.example.kino.features.content.data.api

import androidx.room.*
import com.example.kino.features.content.data.models.Movie

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getAll(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg movie: Movie)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg movie: Movie)

    @Delete
    fun delete(movie: Movie)
}

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
}

