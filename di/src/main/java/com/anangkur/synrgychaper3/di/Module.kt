package com.anangkur.synrgychaper3.di

import android.content.Context
import android.location.LocationManager
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import com.anangkur.synrgychapter3.data.datasource.AuthLocalDataSource
import com.anangkur.synrgychapter3.data.datasource.AuthRemoteDataSource
import com.anangkur.synrgychapter3.data.datasource.MovieLocalDataSource
import com.anangkur.synrgychapter3.data.datasource.MovieRemoteDataSource
import com.anangkur.synrgychapter3.data.datasource.local.AuthLocalDataSourceImpl
import com.anangkur.synrgychapter3.data.datasource.local.MovieLocalDataSourceImpl
import com.anangkur.synrgychapter3.data.datasource.local.datastore
import com.anangkur.synrgychapter3.data.datasource.local.room.MovieDao
import com.anangkur.synrgychapter3.data.datasource.local.room.RoomDatabase
import com.anangkur.synrgychapter3.data.datasource.remote.AuthRemoteDataSourceImpl
import com.anangkur.synrgychapter3.data.datasource.remote.MovieRemoteDataSourceImpl
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.ReqresService
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.TMDBService
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.provideReqresService
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.provideTMDBService
import com.anangkur.synrgychapter3.data.repository.AuthRepositoryImpl
import com.anangkur.synrgychapter3.data.repository.MovieRepositoryImpl
import com.anangkur.synrgychapter3.domain.repository.AuthRepository
import com.anangkur.synrgychapter3.domain.repository.MovieRepository
import com.anangkur.synrgychapter3.domain.usecase.LoginUseCase

class Module(context: Context) {
    val locationManager: LocationManager by lazy {
        context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    val loginUseCase: LoginUseCase by lazy {
        LoginUseCase(
            repository = authRepository,
        )
    }

    val authRepository: AuthRepository by lazy {
        AuthRepositoryImpl(
            authLocalDataSource = authLocalDataSource,
            authRemoteDataSource = authRemoteDataSource,
        )
    }

    val movieRepository: MovieRepository by lazy {
        MovieRepositoryImpl(
            remoteDataSource = movieRemoteDataSource,
            localDataSource = movieLocalDataSource,
        )
    }

    private val movieLocalDataSource: MovieLocalDataSource by lazy {
        MovieLocalDataSourceImpl(
            movieDao = movieDao,
        )
    }

    private val roomDatabase: RoomDatabase by lazy {
        Room.databaseBuilder(
            context = context,
            name = RoomDatabase.NAME,
            klass = RoomDatabase::class.java,
        ).build()
    }

    private val movieDao: MovieDao by lazy {
        roomDatabase.movieDao()
    }

    private val movieRemoteDataSource: MovieRemoteDataSource by lazy {
        MovieRemoteDataSourceImpl(
            tmdbService = tmdbService,
        )
    }

    private val tmdbService: TMDBService by lazy {
        provideTMDBService(context)
    }

    private val authLocalDataSource: AuthLocalDataSource by lazy {
        AuthLocalDataSourceImpl(
            dataStore = dataStore,
        )
    }

    private val dataStore: DataStore<Preferences> by lazy {
        context.datastore
    }

    private val authRemoteDataSource: AuthRemoteDataSource by lazy {
        AuthRemoteDataSourceImpl(
            reqresService = reqresService,
        )
    }

    private val reqresService: ReqresService by lazy {
        provideReqresService(context)
    }
}