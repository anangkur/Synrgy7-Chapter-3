package com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anangkur.synrgychapter3.domain.model.Movie
import com.anangkur.synrgychapter3.domain.repository.MovieRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(
    private val movieRepository: MovieRepository,
) : ViewModel() {

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