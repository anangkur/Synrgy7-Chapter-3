package com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.favorite

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import androidx.savedstate.SavedStateRegistryOwner
import com.anangkur.synrgychapter3.data.datasource.local.MovieLocalDataSourceImpl
import com.anangkur.synrgychapter3.data.datasource.local.room.RoomDatabase
import com.anangkur.synrgychapter3.data.datasource.remote.MovieRemoteDataSourceImpl
import com.anangkur.synrgychapter3.data.datasource.remote.retrofit.provideTMDBService
import com.anangkur.synrgychapter3.data.repository.MovieRepositoryImpl
import com.anangkur.synrgychapter3.domain.repository.MovieRepository
import com.anangkur.synrgychapter3.domain.model.Movie
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    companion object {
        fun provideFactory(
            owner: SavedStateRegistryOwner,
            context: Context,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, null) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle,
                ): T {
                    val roomDatabase = Room.databaseBuilder(
                        context = context,
                        name = RoomDatabase.NAME,
                        klass = RoomDatabase::class.java,
                    ).build()
                    val movieRepository: MovieRepository = MovieRepositoryImpl(
                        remoteDataSource = MovieRemoteDataSourceImpl(
                            tmdbService = provideTMDBService(context),
                        ),
                        localDataSource = MovieLocalDataSourceImpl(
                            movieDao = roomDatabase.movieDao(),
                        ),
                    )
                    return FavoriteViewModel(
                        movieRepository = movieRepository,
                    ) as T
                }
            }
    }

    private val _movies = MutableLiveData<List<Movie>>()
    val movies: LiveData<List<Movie>> = _movies

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    fun getMovieFromLocal() {
        viewModelScope.launch {
            try {
                _loading.value = true
                _movies.value = movieRepository.loadAllMovie()
                _loading.value = false
            } catch (throwable: Throwable) {
                _loading.value = false
                _error.value = throwable
            }
        }
    }
}