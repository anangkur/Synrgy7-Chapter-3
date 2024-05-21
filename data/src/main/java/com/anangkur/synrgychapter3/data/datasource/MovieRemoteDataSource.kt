package com.anangkur.synrgychapter3.data.datasource

import com.anangkur.synrgychapter3.domain.model.Movie

interface MovieRemoteDataSource {
    suspend fun fetchData(): List<Movie>
}