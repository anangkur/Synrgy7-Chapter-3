package com.anangkur.synrgychapter3.data.datasource

import com.anangkur.synrgychapter3.data.datasource.local.room.MovieEntity
import com.anangkur.synrgychapter3.domain.model.Movie

interface MovieLocalDataSource {
    fun storeDataToLocalDb(data: Movie)

    suspend fun insertMovie(movieEntity: MovieEntity)

    suspend fun deleteMovie(movieEntity: MovieEntity)

    suspend fun selectMovieById(id: Int): MovieEntity?

    suspend fun selectAllMovies(): List<MovieEntity>
}