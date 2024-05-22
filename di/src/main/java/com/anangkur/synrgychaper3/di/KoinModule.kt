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
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val koinModule = module {
    single<LocationManager> { androidContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager }
    single<LoginUseCase> { LoginUseCase(repository = get()) }
    single<AuthRepository> { AuthRepositoryImpl(authLocalDataSource = get(), authRemoteDataSource = get()) }
    single<MovieRepository> { MovieRepositoryImpl(remoteDataSource = get(), localDataSource = get()) }
    single<MovieLocalDataSource> { MovieLocalDataSourceImpl(movieDao = get()) }
    single<RoomDatabase> { Room.databaseBuilder(context = get(), name = RoomDatabase.NAME, klass = RoomDatabase::class.java).build() }
    single<MovieDao> { (get() as RoomDatabase).movieDao() }
    single<MovieRemoteDataSource> { MovieRemoteDataSourceImpl(tmdbService = get()) }
    single<TMDBService> { provideTMDBService(get()) }
    single<AuthLocalDataSource> { AuthLocalDataSourceImpl(dataStore = get()) }
    single<DataStore<Preferences>> { androidContext().datastore }
    single<AuthRemoteDataSource> { AuthRemoteDataSourceImpl(reqresService = get()) }
    single<ReqresService> { provideReqresService(get()) }
}