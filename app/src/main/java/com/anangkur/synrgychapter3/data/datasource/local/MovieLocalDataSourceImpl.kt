package com.anangkur.synrgychapter3.data.datasource.local

import com.anangkur.synrgychapter3.data.datasource.MovieLocalDataSource
import com.anangkur.synrgychapter3.data.datasource.local.room.MovieDao
import com.anangkur.synrgychapter3.data.datasource.local.room.MovieEntity
import com.anangkur.synrgychapter3.ui.dataclass.Movie

class MovieLocalDataSourceImpl(
    private val movieDao: MovieDao,
) : MovieLocalDataSource {

    override fun storeDataToLocalDb(data: Movie) {
        // do something
    }

    override suspend fun insertMovie(movieEntity: MovieEntity) {
        movieDao.insertMovie(movieEntity)
    }

    override suspend fun deleteMovie(movieEntity: MovieEntity) {
        movieDao.deleteMovie(movieEntity)
    }

    override suspend fun selectMovieById(id: Int): MovieEntity? {
        return movieDao.selectMovieById(id)
    }

    override suspend fun selectAllMovies(): List<MovieEntity> {
        return movieDao.selectAllMovies()
    }
}