package com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.third

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
import com.anangkur.synrgychapter3.data.repository.MovieRepositoryImpl
import com.anangkur.synrgychapter3.domain.MovieRepository
import com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second.SecondNavigationViewModel
import com.anangkur.synrgychapter3.ui.dataclass.Movie
import kotlinx.coroutines.launch

class ThirdNavigationLogic(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    companion object {
        fun provideFactory(
            owner: SavedStateRegistryOwner,
            context: Context,
        ) =
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
                        remoteDataSource = MovieRemoteDataSourceImpl(),
                        localDataSource = MovieLocalDataSourceImpl(
                            movieDao = roomDatabase.movieDao(),
                        ),
                    )
                    return ThirdNavigationLogic(
                        movieRepository = movieRepository,
                    ) as T
                }
            }
    }

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    var title: String? = null

    fun getUrlGoogle(title: String): String {
        return "https://www.google.com/search?q=$title"
    }

    fun retrieveList(): List<Movie> {
        return emptyList()
    }

    fun saveMovieToFavorite(
        name: String,
        description: String,
        image: String,
        id: Int = -1,
    ) {
        viewModelScope.launch {
            val movie = Movie(
                image = image,
                title = name,
                description = description,
                id = if (id == -1) {
                    null
                } else {
                    id
                },
            )
            movieRepository.saveFavorite(movie)
        }
    }

    fun deleteMovieFromFavorite(movie: Movie) {
        viewModelScope.launch {
            movieRepository.deleteMovie(movie)
        }
    }

    private val _movieLocal = MutableLiveData<Movie?>()
    val movieLocal: LiveData<Movie?> = _movieLocal
    fun loadMovieFromFavorite(id: Int) {
        viewModelScope.launch {
            try {
                _movieLocal.value = movieRepository.loadMovieById(id)
            } catch (throwable: Throwable) {
                _error.value = throwable
            }
        }
    }
}