package com.anangkur.synrgychapter3.domain

import com.anangkur.synrgychapter3.ui.dataclass.Movie

interface MovieRepository {
    suspend fun fetchData(): List<Movie>

    fun storeData(data: Movie)

    suspend fun saveFavorite(movie: Movie)

    suspend fun loadAllMovie(): List<Movie>

    suspend fun deleteMovie(movie: Movie)

    suspend fun loadMovieById(id: Int): Movie?
}