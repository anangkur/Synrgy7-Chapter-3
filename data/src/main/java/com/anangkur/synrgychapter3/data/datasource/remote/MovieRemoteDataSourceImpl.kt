package com.anangkur.synrgychapter3.data.datasource.remote

import com.anangkur.synrgychapter3.data.datasource.MovieRemoteDataSource
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.TMDBService
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.model.toMovie
import com.anangkur.synrgychapter3.domain.model.Movie

class MovieRemoteDataSourceImpl(
    private val tmdbService: TMDBService,
) : MovieRemoteDataSource {
    override suspend fun fetchData(): List<Movie> {
        return tmdbService.getTrendingMovies().results.map { it.toMovie() }
    }
}