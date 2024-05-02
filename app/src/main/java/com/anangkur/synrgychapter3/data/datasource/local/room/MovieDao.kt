package com.anangkur.synrgychapter3.data.datasource.local.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface MovieDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovie(movieEntity: MovieEntity)

    @Delete
    suspend fun deleteMovie(movieEntity: MovieEntity)

    @Query("SELECT * FROM movie WHERE id = :id")
    suspend fun selectMovieById(id: Int): MovieEntity?

    @Query("SELECT * FROM movie")
    suspend fun selectAllMovies(): List<MovieEntity>
}