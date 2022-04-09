package com.example.kino.features.content.data.api

import androidx.room.*
import com.example.kino.features.content.data.models.Movie

@Dao
interface MoviesDao {
    // @Query("SELECT * FROM movies LIMIT 20 OFFSET ((:page - 1) * 20)")
    // fun getAll(page: Int): List<Movie>

    @Query("SELECT * FROM movies")
    fun getAll(): List<Movie>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(movie: List<Movie>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg movie: Movie)

    @Query("SELECT * FROM movies WHERE isFavorite IN (:favorite)")
    fun getAllFavorites(favorite: Boolean): List<Movie>

    @Query("UPDATE movies SET isFavorite = :isFavorite WHERE filmId = :id")
    fun setIsFavorite(id: Int, isFavorite: Boolean);

    @Delete
    fun delete(movie: Movie)
}

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao
}
