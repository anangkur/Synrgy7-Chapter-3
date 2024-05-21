package com.anangkur.synrgychapter3.ui.activity.navigationcomponent.fragment.second

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anangkur.synrgychapter3.domain.model.Movie
import com.anangkur.synrgychapter3.domain.repository.MovieRepository
import kotlinx.coroutines.launch

class SecondNavigationViewModel(
    private val repository: MovieRepository,
) : ViewModel() {

    /**
     * Retrieves a list of sample movie data.
     *
     * This function returns a list of sample movie data, each containing an image URL, a title, and a description.
     * The sample movie data is provided for demonstration purposes and can be used to populate UI components such
     * as RecyclerViews with movie items.
     *
     * @return A list of Movie objects containing sample movie data.
     */
    private val _movies: MutableLiveData<List<Movie>> = MutableLiveData()
    val movies: LiveData<List<Movie>> = _movies

    private val _loading: MutableLiveData<Boolean> = MutableLiveData()
    val loading: LiveData<Boolean> = _loading

    private val _error: MutableLiveData<String> = MutableLiveData()
    val error: LiveData<String> = _error

    fun retrieveMovieData() {
        // Create and return a list of sample movie data
        viewModelScope.launch {
            try {
                _loading.value = true
                _movies.value = repository.fetchData()
                _loading.value = false
            } catch (throwable: Throwable) {
                _loading.value = false
                _error.value = throwable.message
            }
        }
    }
}