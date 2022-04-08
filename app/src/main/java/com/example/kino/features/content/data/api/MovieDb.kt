package com.example.kino.features.content.data.api

import androidx.room.*
import com.example.kino.features.content.data.models.Movie

@Dao
interface MoviesDao {
    @Query("SELECT * FROM movies")
    fun getAll(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movie: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movie: Movie)

    @Query("SELECT * FROM movies WHERE is_favorite IN (:favorite)")
    fun getAllFavorites(favorite: Boolean): List<Movie>

    @Delete
    fun delete(movie: Movie)
}

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
}
