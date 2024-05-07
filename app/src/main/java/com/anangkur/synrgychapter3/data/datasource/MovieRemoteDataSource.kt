package com.anangkur.synrgychapter3.data.datasource

import com.anangkur.synrgychapter3.ui.dataclass.Movie

interface MovieRemoteDataSource {
    suspend fun fetchData(): List<Movie>
}