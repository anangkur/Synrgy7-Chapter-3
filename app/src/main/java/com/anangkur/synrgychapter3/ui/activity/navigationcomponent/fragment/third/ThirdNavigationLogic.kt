package com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.third

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anangkur.synrgychapter3.domain.model.Movie
import com.anangkur.synrgychapter3.domain.repository.MovieRepository
import kotlinx.coroutines.launch

class ThirdNavigationLogic(
    private val movieRepository: MovieRepository,
) : ViewModel() {

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    var title: String? = null

    fun getUrlGoogle(title: String): String {
        return "https://www.google.com/search?q=$title"
    }

    fun retrieveList(): List<Movie> {
        return emptyList()
    }

    private val _insertMovie = MutableLiveData<Boolean>()
    val insertMovie: LiveData<Boolean> = _insertMovie

    fun saveMovieToFavorite(
        name: String,
        description: String,
        image: String,
        id: Int = -1,
    ) {
        viewModelScope.launch {
            try {
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
                _insertMovie.value = true
            } catch (throwable: Throwable) {
                _error.value = throwable
            }
        }
    }

    private val _deleteMovie = MutableLiveData<Boolean>()
    val deleteMovie: LiveData<Boolean> = _deleteMovie

    fun deleteMovieFromFavorite(movie: Movie) {
        viewModelScope.launch {
            try {
                movieRepository.deleteMovie(movie)
                _deleteMovie.value = true
            } catch (throwable: Throwable) {
                _error.value = throwable
            }
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