package com.anangkur.synrgychapter3.data.repository

import com.anangkur.synrgychapter3.data.datasource.MovieLocalDataSource
import com.anangkur.synrgychapter3.data.datasource.MovieRemoteDataSource
import com.anangkur.synrgychapter3.domain.MovieRepository
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.data.Movie

class MovieRepositoryImpl(
    private val remoteDataSource: MovieRemoteDataSource,
    private val localDataSource: MovieLocalDataSource,
) : MovieRepository {
    override fun fetchData(): List<Movie> {
        return remoteDataSource.fetchData()
    }

    override fun storeData(data: Movie) {
        localDataSource.storeDataToLocalDb(data)
    }
}