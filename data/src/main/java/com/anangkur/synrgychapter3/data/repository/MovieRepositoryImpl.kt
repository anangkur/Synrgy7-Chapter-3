package com.anangkur.synrgychapter3.data.repository

import com.anangkur.synrgychapter3.data.datasource.MovieLocalDataSource
import com.anangkur.synrgychapter3.data.datasource.MovieRemoteDataSource
import com.anangkur.synrgychapter3.data.model.mapper.toMovie
import com.anangkur.synrgychapter3.data.model.mapper.toMovieEntity
import com.anangkur.synrgychapter3.data.model.mapper.toMovies
import com.anangkur.synrgychapter3.domain.repository.MovieRepository
import com.anangkur.synrgychapter3.domain.model.Movie

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
) : MovieRepository {
    override suspend fun fetchData(): List<Movie> {
        return remoteDataSource.fetchData()
    }

    override fun storeData(data: Movie) {
        localDataSource.storeDataToLocalDb(data)
    }

    override suspend fun saveFavorite(movie: Movie) {
        localDataSource.insertMovie(movie.toMovieEntity())
    }

    override suspend fun loadAllMovie(): List<Movie> {
        return localDataSource.selectAllMovies().toMovies()
    }

    override suspend fun deleteMovie(movie: Movie) {
        localDataSource.deleteMovie(movie.toMovieEntity())
    }

    override suspend fun loadMovieById(id: Int): Movie? {
        return localDataSource.selectMovieById(id)?.toMovie()
    }
}